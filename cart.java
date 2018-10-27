import java.util.*;
public class cart {
	private ArrayList<product> prods;
	private ArrayList<Integer> qt;
	private double total;
	public ArrayList<product> getProds() {
		return prods;
	}
	public void setProds(ArrayList<product> prods) {
		this.prods = prods;
	}
	public cart() {
		prods = new ArrayList<product>();
		qt=new ArrayList<Integer>();
		total=0;
	}
	public void add(product p,int qty) {
		prods.add(p);
		qt.add(qty);
		total += p.getPrice();
	}
	
	
	public boolean check(product p) {
		for(int i=0;i<prods.size();i++) {
			if(prods.get(i).getName().equals(p.getName())) return true;
		}
		return false;
	}
	public int getsizeprods() {
		return this.prods.size();
	}
	public int getsizeqt() {
		return this.qt.size();
	}
	public product getprodat(int i) {
		return prods.get(i);
	}
	public int getqtat(int i) {
		return qt.get(i);
	}
	public ArrayList<Integer> getQt() {
		return qt;
	}
	public void setQt(ArrayList<Integer> qt) {
		this.qt = qt;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}
