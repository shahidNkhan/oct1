import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
//remember to pass database to customer pass another object which has only the get functions and setnunits
public class man{

	public static void main(String[] args)throws IOException,ClassNotFoundException,InvalidOptionException,productnotfound,categorynotfound,DuplicateProductException,Insufficientfunds,outofstockexception,InvalidInputException,incorrectpathexception {
		// TODO Auto-generated constructor stub
		int option;String so;char o;Scanner sc = new Scanner(System.in);
		admin a = null;database d = null;
		ObjectInputStream in2 = null;
		try {
			in2 = new ObjectInputStream(new FileInputStream("data.txt"));
			d = (database)in2.readObject();
		}
		catch(EOFException e) {
			d = new database();
		}catch(FileNotFoundException e){
			d = new database();
		}finally {
			in2.close();
		}
		ObjectInputStream inn1 = null;
		try {
			inn1 = new ObjectInputStream(new FileInputStream("admin.txt"));
			a = (admin)inn1.readObject();
			inn1.close();
		}
		catch(EOFException e) {
			a = new admin(d);
		}
		catch(FileNotFoundException e) {
			a = new admin(d);
			
		}
		finally {
			
		}
		do {
			System.out.println("Select one of the following: \n1)Administrator\n2)Customer\n3)Exit Amacon");
			option=sc.nextInt();
			if(option==1) {
				do {
				System.out.println("Select one of the following: \na)Insert product/category \nb)Delete product/category "
						+ "\nc)Search product/category \nd)Modify product \ne)Exit as administrator");
				so = sc.next();
				o = so.charAt(0);
				if(o=='a'||o=='A') {
					System.out.println("Insert path: ");
					sc.nextLine();
					String s1 = sc.nextLine();
					System.out.println("Insert name of the product: ");
					String s2 = sc.nextLine();
					try{
						a.insert(s1, s2);
					}
					catch(DuplicateProductException d1) {
						System.out.println(d1.getMessage());
					}
					catch(InvalidInputException i1) {
						System.out.println(i1.getMessage());
					}
				}
				else if(o=='b'||o=='B') {
//					System.out.println("Enter the ");
					System.out.println("Enter the path of the product/category to be deleted: ");
					sc.nextLine();
					String s = sc.nextLine();
					try {
						a.d.del(s);
					}catch(incorrectpathexception er) {
						System.out.println(er.toString());
					}
					catch(productnotfound p1) {
						System.out.println(p1.getMessage());
					}
					catch(categorynotfound c1) {
						System.out.println(c1.getMessage());
					}
//					String[] st = s.split(" > ");
//					a.d.delete(st[st.length-1]);
				}
				else if(o=='c'||o=='C') {
					System.out.println("Enter the name of the product");
					sc.nextLine();
					String s = sc.nextLine();
					try{
						product p =a.search(s);
						if(p!=null) {
							System.out.println(p);
						}
					}
					catch(productnotfound p1) {
						System.out.println(p1.toString());
					}
					
				}
				else if(o=='d'||o=='D') {
					try{
						a.modify();
					}
					catch(productnotfound p1) {
						System.out.println(p1.toString());
					}
				}
				else if(o=='e'||o=='E') {
					break;
				}
				else {
					throw new InvalidOptionException("option has to be between a and e");
				}
				}while((o<='e'&&o>='a')||(o<='E'&&o>='A'));
//				a.d.dp();
			}
			else if(option==2) {
				ObjectInputStream cin = null;
				ArrayList<customer> cl=null;
				try {
					cin = new ObjectInputStream(new FileInputStream("cust.txt"));
					cl = new ArrayList<customer>();
					try {
						customer tempc = (customer)cin.readObject();
						cl.add(tempc);
					}catch(EOFException er) {
						
					}
					finally {
						
					}
					
					System.out.println("Enter the userid of the Customer: ");
					String user = sc.next();
					customer c = null;
					int flag = 0;
					for(int i=0;i<cl.size();i++) {
						if(cl.get(i).checkuserid(user) == true) {
							c = cl.get(i);
							flag = 1;
							System.out.println("Customer exists");
							break;
						}
					}
					if(flag==0) {
						c = new customer(user);
						
						cl.add(c);
						System.out.println("New Customer created");
					}
					do {
						System.out.println("Select one of the following: \na)Add funds\nb)Add product to cart\nc)Check-out cart "
								+ "\nd)Exit as customer");
						so = sc.next();
						o = so.charAt(0);
						if(o=='a'||o=='A') {
							System.out.println("Enter the amount you want to add: ");
							double amt = sc.nextDouble();
							c.addfunds(amt);
							
						}
						else if(o=='b'||o=='B') {
							System.out.println("Enter the name of the product to be added: ");
	//						sc.nextLine();
							String spn = sc.next();
							try {
								product pt = a.search(spn);
								c.addprod(pt);
							}catch(productnotfound er) {
								System.out.println(er.toString());
								continue;
							}						
						}
						else if(o=='c'||o=='C') {
							//the checkout in admin needs 3 exception handling
							try{
								a.checkout(c);
							}
							catch(outofstockexception e1) {
								System.out.println(e1.toString());
							}
							catch(Insufficientfunds e2) {
								System.out.println(e2.toString());
							}
							catch(productnotfound e3) {
								System.out.println(e3.toString());
							}
						}
						else if(o=='d'||o=='D') {
							break;
						}
						else {
							throw new InvalidOptionException("option should be between a and d");
						}
					}while((o<='d'&&o>='a')||(o<='D'&&o>='A'));
				}finally {
					cin.close();
					ObjectOutputStream out = null;
					try {
						if(cl==null) break;
						File file = new File("C:\\Users\\Shahid\\eclipse-workspace\\lab05\\cust.txt");
						file.delete();
						out = new ObjectOutputStream ( new FileOutputStream("cust.txt")); 
						for(int i = 0;i<cl.size();i++) {
							out.writeObject(cl.get(i));
						}
					}
					finally {
						out.close();
					}
				}
			}
			if(option==3) {break;}
			if(option>3||option<1) {
				throw new InvalidOptionException("option has to be between 1 and 3");
			}
		}while(option<3&&option>0);
		File file1=null;
		try{
			file1 = new File("C:\\Users\\Shahid\\eclipse-workspace\\lab05\\admin.txt");
			file1.delete();
		}
		finally {
		}
		File file2=null;
		try{
			file2 = new File("C:\\Users\\Shahid\\eclipse-workspace\\lab05\\data.txt");
			file2.delete();
		}
		finally {
		}
		ObjectOutputStream o1 = null;
		try {
			o1 = new ObjectOutputStream(new FileOutputStream("admin.txt"));
			o1.writeObject(a);
		}
		finally {
			o1.close();
		}
		ObjectOutputStream o2 = null;
		try {
			o2 = new ObjectOutputStream(new FileOutputStream("data.txt"));
			o2.writeObject(d);
		}finally {
			o2.close();
		}
	}

}


 