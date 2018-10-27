import java.util.ArrayList;
import java.util.Scanner;
//remember to pass database to customer pass another object which has only the get functions and setnunits
public class man {
	
	public static void main(String[] args)throws InvalidOptionException,productnotfound,categorynotfound,Insufficientfunds,InvalidInputException {
		// TODO Auto-generated constructor stub
		int option;String so;char o;Scanner sc = new Scanner(System.in);
		admin a = new admin();
		customer c = new customer();
		
		c.setdatabase(a.d);
		do {
			System.out.println("Select one of the following: \n1)Administrator\n2)Customer\nExit Amacon");
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
					a.insert(s1, s2);
				}
				else if(o=='b'||o=='B') {
//					System.out.println("Enter the ");
					System.out.println("Enter the path of the product/category to be deleted: ");
					sc.nextLine();
					String s = sc.nextLine();
					String[] st = s.split(" > ");
					a.d.delete(st[st.length-1]);
				}
				else if(o=='c'||o=='C') {
					System.out.println("Enter the name of the product");
					sc.nextLine();
					String s = sc.nextLine();
					product p =a.search(s);
					if(p!=null) {
						System.out.println(p);
					}
				}
				else if(o=='d'||o=='D') {
					System.out.println("Enter the product to be modified");
					sc.nextLine();
					String s = sc.nextLine();
				}
				else if(o=='e'||o=='E') {
					break;
				}
				else {
					throw new InvalidOptionException("option has to be between a and e");
				}
				}while((o<='e'&&o>='a')||(o<='E'&&o>='A'));
				a.d.dp();
			}
			else if(option==2) {
				do {
					System.out.println("Select one of the following: \na)Add funds\nb)Add product to cart "
							+ "\nc)Check-out cart \nd)Exit as customer");
					so = sc.next();
					o = so.charAt(0);
					if(o=='a'||o=='A') {
						System.out.println("Enter the amount you want to add: ");
						double amt = sc.nextDouble();
						c.addfunds(amt);
						
					}
					else if(o=='b'||o=='B') {
						System.out.println("Enter the name of the product to be added: ");
						sc.nextLine();
						String spn = sc.nextLine();
						c.addprod(spn);
					}
					else if(o=='c'||o=='C') {
						
						double amt =c.checkout();
						a.addmoney(amt);
					}
					else if(o=='d'||o=='D') {
						break;
					}
					else {
						throw new InvalidOptionException("option should be between a and d");
					}
				}while((o<='d'&&o>='a')||(o<='D'&&o>='A'));
			}
			if(option==3) {break;}
			if(option>3||option<1) {
				throw new InvalidOptionException("option has to be between 1 and 3");
			}
		}while(option<3&&option>0);
		
	}

}
