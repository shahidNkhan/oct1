import java.io.Serializable;
import java.util.*;
public class category implements Serializable {
	private String cname;
	private ArrayList<category> subcategory;
	private ArrayList<product> products;
	public category(String cname) {
		// TODO Auto-generated constructor stub
		this.cname=cname;
		subcategory = new ArrayList<category>();
		products = new ArrayList<product>();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o!=null && o.getClass()==this.getClass()) {
			category c = (category) o;
			int index=0;
			if((this.getCname().equalsIgnoreCase(c.getCname()))==false) return false;
			if(this.getcatlistsize() != c.getcatlistsize()) return false;
			if(this.getproductlistsize() != c.getproductlistsize()) return false;
			for(int i=0;i<this.getcatlistsize();i++) {
				if(this.categoryat(i).equals(c.categoryat(i))==false) return false;
			}
			for(int i=0;i<this.getproductlistsize();i++) {
				if(this.getproductat(i).equals(c.getproductat(i)) == false) return false;
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public void inscat(ArrayList<String> st,int index,String ps)throws InvalidInputException {
		Scanner sc= new Scanner(System.in);
		if(index==st.size()) {
			int found=0;
			for(int i=0;i<products.size();i++) {
				if(products.get(i).getName().equals(ps)) {
					found=1;
					break;
				}
			}
			if(found==0) {
				System.out.println("Enter the price per unit of "+ ps + ": ");
				double price = sc.nextDouble();
				System.out.println("Enter the number of units of "+ ps + ": ");
				int nunits = sc.nextInt();
				if(nunits<0) throw new InvalidInputException("no of units can only be greater than ");
				product pt = new product(ps,price,nunits);
				this.setprod(pt);
			}
		}
		if(index>=st.size()) return;
		int i,j;String s=st.get(index);int flag=0;
		for(i=0;i<this.getcatlistsize();i++) {
			category c = subcategory.get(i);
			if(c.getCname().equalsIgnoreCase(s)) {
				c.inscat(st, index+1,ps);
				return;
			}
		}
		category temp = new category(s);
		this.getadd(temp);
		temp.inscat(st, index+1,ps);
	}
	
	public void inscategorytest(ArrayList<String> st,int index,String ps,double price,int nunits)throws InvalidInputException {
		Scanner sc= new Scanner(System.in);
		if(index==st.size()) {
			int found=0;
			for(int i=0;i<products.size();i++) {
				if(products.get(i).getName().equals(ps)) {
					found=1;
					break;
				}
			}
			if(found==0) {
				if(nunits<0) throw new InvalidInputException("no of units can only be greater than ");
				product pt = new product(ps,price,nunits);
				this.setprod(pt);
			}
		}
		if(index>=st.size()) return;
		int i,j;String s=st.get(index);int flag=0;
		for(i=0;i<this.getcatlistsize();i++) {
			category c = subcategory.get(i);
			if(c.getCname().equalsIgnoreCase(s)) {
				c.inscategorytest(st, index+1,ps,price,nunits);
				return;
			}
		}
		category temp = new category(s);
		this.getadd(temp);
		temp.inscategorytest(st, index+1,ps,price,nunits);
	}
	
	public category searchcat_under(String s1) throws productnotfound, categorynotfound {
//		int flag = 0;
		for(int i=0;i<this.getcatlistsize();i++) {
			if(this.getnameofcategoryat(i).equalsIgnoreCase(s1)) {
//				flag = 1;
				return this.categoryat(i);
			}
		}
		throw new categorynotfound("Subcategory does not exist under this category");
	}
	
	public product searchprod_under(String s1)throws productnotfound, categorynotfound {
//		int flag = 0;
		for(int i=0;i<this.getproductlistsize();i++) {
			if(this.getnameofproductat(i).equalsIgnoreCase(s1)) {
//				flag = 1;
				return this.getproductat(i);
			}
		}
		throw new productnotfound("Product does not exist under this category");
	}
	
	public category searchcat(String cat) {
		if(this.getCname().equals(cat)) {
			return this;
		}
		category temp=null;
		for(int i=0;i<subcategory.size();i++) {
			temp = subcategory.get(i).searchcat(cat);
			if(temp!=null) return temp;
		}
		return null;
	}
	
	public product searchprod(String prod) {
//		System.out.println("hi");
		product temp=null;
		for(int i=0;i<products.size();i++) {
			if(products.get(i).getName().equals(prod)) {
				return products.get(i);
			}
		}
		for(int i=0;i<subcategory.size();i++) {
			temp= subcategory.get(i).searchprod(prod);
			if(temp!=null) return temp;
		}
		return null;
	}
	
	public boolean delp(String prod) {
		for(int i=0;i<products.size();i++) {
			if(products.get(i).getName().equals(prod)) {
				products.remove(i);
				return true;
			}
		}
		for(int i=0;i<subcategory.size();i++) {
			return subcategory.get(i).delp(prod);
		}
		return false;
	}
	
	public void delc() {
//		System.out.println("HERE");
		for(int i=subcategory.size()-1;i>=0;i--) {
			subcategory.remove(i);
		}
		for(int i=products.size()-1;i>=0;i--) {
			products.remove(i);
		}
	}
	
	public String getnameofcategoryat(int index) throws productnotfound,categorynotfound{
		String stemp="";
		if(index<this.getcatlistsize()) return subcategory.get(index).cname;
		else return stemp;
	}
	public void dp() {
		System.out.println(getCname());
		for(int i=0;i<this.getcatlistsize();i++) {
			subcategory.get(i).dp();
		}
		for(int i=0;i<this.getproductlistsize();i++) {
			System.out.println(products.get(i));
		}
	}
	
	public String toString() {
		String s = this.getCname();
		for(int i=0;i<this.getcatlistsize();i++) {
			s = s + " " + subcategory.get(i).toString();
		}
		for(int i=0;i<this.getproductlistsize();i++) {
			s = s + " " + products.get(i).toString();
		}
		return s;
	}
	
	public void getadd(category cate) {
		subcategory.add(cate);
	}	
	public int getproductlistsize() {
		return products.size();
	}
	public int getcatlistsize() {
		return subcategory.size();
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public void setprod(product p) {
		products.add(p);		
	}
	public category categoryat(int index) {
		if(index<this.getcatlistsize()) return this.subcategory.get(index);
		else return null;
	}
	public String getnameofproductat(int index) {
		if(index<this.getproductlistsize()) return this.products.get(index).getName();
		else return "";
	}
	public product getproductat(int index) {
		if(index<this.getproductlistsize()) return this.products.get(index);
		else return null;
	}
}
