import java.io.Serializable;
import java.util.Scanner;

public class admin implements Serializable{
	private double money;
	database d;
	public admin(database db) {
		// TODO Auto-generated constructor stub
		money = 0;
		d = db;
	}
	public void addmoney(double d) {
		this.money+=d;
	}

	public void insert(String s1,String s2)throws InvalidInputException,DuplicateProductException {
		try{
			d.inscategory(s1, s2);
		}catch(DuplicateProductException d1) {
			throw new DuplicateProductException(d1.getMessage());
		}
		catch(InvalidInputException i1) {
			throw new InvalidInputException(i1.getMessage());
		}
	}
	public product search(String s)throws productnotfound {
		product p=null;
		try{
			p=d.searchprod(s);
		}
		catch(productnotfound e) {
			throw new productnotfound("The product could not be found in the database");
		}
		return p;
	}
	
	public void modify() throws productnotfound{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the product to be modified");
		String s = sc.nextLine();
		product p;
		try{
			p = d.searchprod(s);
			d.modif(p);
		}
		catch(productnotfound c1) {
			throw new productnotfound(c1.getMessage());
		}
	}
	
	public void checkout(customer cust)throws productnotfound,outofstockexception,Insufficientfunds {
		cart c = cust.getcart();
		double tote=0;
		for(int i=0;i<c.getsizeprods();i++) {
			product p = c.getprodat(i);
			int qt_required = c.getqtat(i);
			try {
			d.searchprod(p.getName());
			}catch(productnotfound er) {
				throw new productnotfound(er.getMessage());
			}
			if(p.getNunits()<qt_required) {
				throw new outofstockexception("sufficient quantity of product not in stock");
			}
			tote = tote + ((p.getPrice())*qt_required);
		}
		if(tote>cust.getFunds()) {
			throw new Insufficientfunds("Insufficient funds");
		}
		System.out.println("\nCheck-out Successful!");
		cust.setFunds(cust.getFunds() - tote);
		this.addmoney(tote);
		for(int i=0;i<c.getsizeprods();i++) {
			product p = c.getprodat(i);
			int qt_required = c.getqtat(i);
			p.modify(p.getNunits()-qt_required);
		}
		cust.clearcart();
	}
}
