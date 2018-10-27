import java.util.*;
public class category {
	private String cname;
	private ArrayList<category> subcategory;
	private ArrayList<product> products;
	public category(String cname) {
		// TODO Auto-generated constructor stub
		this.cname=cname;
		subcategory = new ArrayList<category>();
		products = new ArrayList<product>();
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
		System.out.println("HERE");
		for(int i=subcategory.size()-1;i>=0;i--) {
			subcategory.remove(i);
		}
		for(int i=products.size()-1;i>=0;i--) {
			products.remove(i);
		}
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
}
