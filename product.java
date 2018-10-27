import java.io.Serializable;

public class product implements Serializable{
	private String name;
	private double price;
	private int nunits;
	
	public void modify(int n) {
		this.setNunits(n);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o!=null && o.getClass()==this.getClass()) {
			product p = (product) o;
			if( p.name.equals(this.getName()) && p.getNunits() == this.getNunits() && p.getPrice() == this.getPrice() ) return true;
			else return false;
		}
		else return false;
	}
	
	public product(String name,double price,int nunits) {
		// TODO Auto-generated constructor stub
		this.setName(name);
		this.setNunits(nunits);
		this.setPrice(price);
	}
	
	public boolean equals(product other) {
		return this.getName().equals(other.getName());
	}
	
	public void modify(int nob,double dob) {
		this.setNunits(nob);
		this.setPrice(dob);
	}
	
	public String toString() {
		return this.getName();// + " " + this.getNunits() + " " + this.getPrice();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNunits() {
		return nunits;
	}
	public void setNunits(int nunits) {
		this.nunits = nunits;
	}

}
