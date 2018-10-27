import java.io.Serializable;
import java.util.*;
public class customer implements Serializable{
	String userid;
	private double funds;
	private cart mycart;
	public customer(String userid) {
		mycart = new cart();
		this.setuserid(userid);
		funds=0;
		// TODO Auto-generated constructor stub
	}
	public void addfunds(double dd) {
		this.funds+=dd;
	}
	public void clearcart() {
		this.mycart = new cart();
	}
	public void addprod(product p){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter quantity: ");
		int qt = sc.nextInt();
		if(mycart.check(p)==true) {
			System.out.println("Product already exists in cart");//ask if they want to increase?
			return;
		}
		mycart.add(p, qt);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o==null) return false;
//		System.out.println("ji");
		if(o.getClass() != this.getClass()) return false;
		customer cm = (customer) o;
//		System.out.println("ji");
		if(cm.mycart.equals(this.mycart) == false) return false;
		return true;
	}
	
	public void addproducttest(product p,int qt)throws outofstockexception{
		Scanner sc = new Scanner(System.in);
		if(mycart.check(p)==true) {
			System.out.println("Product already exists in cart");//ask if they want to increase?
			return;
		}
		if(p.getNunits()<qt) throw new outofstockexception("Product out of stock");
		mycart.add(p, qt);
	}
	//removeprods in customer is never used 
	public void removeprods() {
		for(int i=0;i<mycart.getsizeprods();i++) {
			mycart.getprodat(i).setNunits(mycart.getqtat(i));
		}
	}
	//checkout in customer is never used
	public double checkout() {
		double money=0;
		if(this.getFunds()>=mycart.getTotal()) {
			this.setFunds(this.getFunds()-mycart.getTotal());
			money = mycart.getTotal();
			removeprods();
			mycart = new cart();
		}
		else {
			System.out.println("Insufficient funds");
		}
		return money;
	}
	public double getFunds() {
		return funds;
	}
	public void setFunds(double funds) {
		this.funds = funds;
	}
	public cart getcart() {
		return this.mycart;
	}
	public void setuserid(String userid) {
		this.userid = userid;
	}
	public  String getuserid() {
		return this.userid;
	}
	public boolean checkuserid(String user) {

		if(this.userid.equals(user)) return true;
		return false;
	}
	
	public String toString() {
		String s="";
		s += mycart.toString();
		return s;
	}
}
