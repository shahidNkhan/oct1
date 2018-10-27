
public class admin {
	private double money;
	database d;
	public admin() {
		// TODO Auto-generated constructor stub
		money = 0;
		d = new database();
	}
	public void addmoney(double d) {
		this.money+=d;
	}

	public void insert(String s1,String s2)throws InvalidInputException {
		d.inscategory(s1, s2);
	}
	public product search(String s)throws productnotfound {
		product p=null;
		try{
			p=d.searchprod(s);
		}
		catch(productnotfound e) {
			System.out.println(e.getMessage());
		}
		return p;
	}
}
