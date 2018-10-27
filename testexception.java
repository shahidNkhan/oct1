import org.junit.Test;

public class testexception {

	@Test(expected = DuplicateProductException.class)
	public void test1()throws DuplicateProductException,InvalidInputException {
		database d = new database();
		String g = "Smartphone > Android";
		String j = "Oneplus";
		d.inscategorytest(g, j,1,1);
		d.inscategorytest(g, j,1,1);
	}
	@Test(expected = incorrectpathexception.class)
	public void test2() throws DuplicateProductException,InvalidInputException,productnotfound,incorrectpathexception,categorynotfound{
		database d = new database();
		String g = "Smartphone > Android";
		String j = "Oneplus";
		d.inscategorytest(g, j,1,1);
		d.del("Smartphone > Nokia");
	}
	@Test(expected = productnotfound.class)
	public void test3() throws DuplicateProductException,InvalidInputException,productnotfound{
		database d = new database();
		String g = "Smartphone > Android";
		String j = "Oneplus";
		d.inscategorytest(g, j, 1, 1);
		d.searchprod("Nokia");
	}
	@Test(expected = outofstockexception.class)
	public void test4() throws DuplicateProductException,InvalidInputException,productnotfound,outofstockexception {
		database d = new database();
		String g = "Smartphone > Android";
		String j = "Oneplus";
		d.inscategorytest(g, j, 0, 0);
		customer c = new customer("temporary");
		product p = d.searchprod("Oneplus");
		c.addproducttest(p, 1);
	}
	@Test(expected = Insufficientfunds.class)
	public void test5() throws Insufficientfunds,DuplicateProductException,InvalidInputException,productnotfound,outofstockexception{
		database d = new database();
		String g = "Smartphone > Android";
		String j = "Oneplus";
		d.inscategorytest(g, j, 200, 200);
		customer c = new customer("temporary");
		product p = d.searchprod("Oneplus");
		c.addproducttest(p, 1);
		c.addfunds(10);
		admin a = new admin(d);
		a.checkout(c);
	}
}
