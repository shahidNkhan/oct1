import java.util.*;

import com.sun.xml.internal.ws.util.StringUtils;
public class database {
	private ArrayList<category> roots;
	HashSet<String> h;
	public database() {
		// TODO Auto-generated constructor stub
		roots = new ArrayList<category>();
		h = new HashSet<String>();
	}
	public void inscategory(String cat,String ps)throws InvalidInputException {
		int i,j;int pos=0;String stemp="";
		for(i=0;i<ps.length();i++) {
			if(i==pos || i==ps.length()-1) {
				if(ps.charAt(i)==' ') {
					pos++;
					continue;
				}
			}
			stemp=stemp+ps.charAt(i);
		}
		ps=stemp;
		stemp = stemp.toLowerCase();
		char c;
		ArrayList<String> st = new ArrayList<String>();
		ArrayList<String> st1 = new ArrayList<String>();
		if(h.contains(stemp)==true) {
			System.out.println("Duplicate element");
			return;
		}
		h.add(stemp);
		String s="",s1="";
		for(i=0;i<cat.length();i++) {
			c=cat.charAt(i);
			if(c=='>') {
				if(s!="") {
					st1.add(s);
				}
				s="";
			}
			else {
				s = s+c;
			}
		}
		if(s!="") st1.add(s);int x=0;
		for(i=0;i<st1.size();i++) {
			s1="";
			s=st1.get(i);
			if(s==""||s==" ") continue;
			int cno=0;
			for(j=0;j<s.length();j++) {
				x=0;
				if(j==x||j==s.length()-1) {
					if(s.charAt(j)==' ') { 
						x++;
						continue;
					}
				}
				s1=s1+s.charAt(j);
				if(Character.isLetterOrDigit(s.charAt(j))) cno++;
			}
			if(cno==0) continue;
			st.add(s1);
		}
		
		category temp=null;int flag=0;
		for(i=0;i<roots.size();i++) {
			category categ1 = roots.get(i);
			if(categ1.getCname().equalsIgnoreCase(st.get(0))) {
				flag=1;
				temp=categ1;
				break;
			}
		}
		if(flag==0) {
			category nc = new category(st.get(0));
			roots.add(nc);
			temp=nc;
		}
		try{
			temp.inscat(st, 1,ps);
		}catch(InvalidInputException err) {
			System.out.println(err.getMessage());
		}
	}
	
	public category searchcat(String cat) {
		category temp=null;
		for(int i=0;i<roots.size();i++) {
			temp=roots.get(i).searchcat(cat);
			if(temp!=null) {
				return temp;
			}
		}
		return temp;
	}
	
	public product searchprod(String prod)throws productnotfound {
		product temp=null;
		for(int i=0;i<roots.size();i++) {
			temp=roots.get(i).searchprod(prod);
			if(temp!=null)
				return temp;
		}
		throw new productnotfound("Product does not exist");
//		return null;
	}
	
	public void delete(String del)throws productnotfound {
		category temp1=null;product temp2 = null;
		temp1 = this.searchcat(del);
		if(temp1!=null) temp1.delc();
		if(searchprod(del)==null) {System.out.println("Product not found"); return;}
		for(int i=0;i<roots.size();i++) {
			boolean b =roots.get(i).delp(del);
			if(b==true) {
				h.remove(del);
			}
		}
	}	
	
	public void modifyprod(String pname)throws productnotfound {
		product tos=null;
		try{
			tos = this.searchprod(pname);
		}catch(productnotfound p) {
			System.out.println(p.getMessage());
		}
		if(tos==null) System.out.println("Product not found");
		else {
			Scanner sc = new Scanner(System.in);
			System.out.println("Current number of units: " + tos.getNunits() + "\nCurrent price per unit: " + tos.getPrice());
			System.out.println("Enter new number of units: ");
			int nob = sc.nextInt();
			System.out.println("Enter new price per unit: ");
			Double dob = sc.nextDouble();
			tos.modify(nob,dob);
			
		}
	}
	
	public void dp() {
		for(int i=0;i<roots.size();i++) {
			roots.get(i).dp();
			System.out.println("\n");
		}
	}
}
