import java.util.*;
public class customer {
	private double funds;
	private cart mycart;
	private database data;
	public customer() {
		mycart = new cart();
		funds=0;
		// TODO Auto-generated constructor stub
	}
	
	public void addfunds(double dd) {
		this.funds+=dd;
	}
	
	public void setdatabase(database d) {
		this.data=d;
	}
	public void addprod(String pname)throws productnotfound {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter quantity: ");
		int qt = sc.nextInt();
		product p = null;
		try { p = data.searchprod(pname);
		}
		catch(productnotfound er) {
			System.out.println(er.getMessage());
		}
		if(p==null) { System.out.println("Product not found");return;}
		if(p.getNunits()>qt) {
			System.out.println("Quantity is greater than number of units in stock");
			return;
		}
		if(mycart.check(p)==true) {
			System.out.println("Product already exists in cart");//ask if they want to increase?
			return;
		}
		mycart.add(p, qt);
	}
	
	public void removeprods() {
		for(int i=0;i<mycart.getsizeprods();i++) {
			mycart.getprodat(i).setNunits(mycart.getqtat(i));
		}
	}
	
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
	
	
}
