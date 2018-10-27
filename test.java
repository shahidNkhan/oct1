import org.junit.*; 
import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream; 
public class test {

	@Test
	public void testdatabase() throws IOException,ClassNotFoundException{
		database d = new database();
		try {
		d.inscategorytest("Electronics > Smartphones > Android", "Oneplus",10,500);
		d.inscategorytest("Electronics > Kitchen", "Microwave",30,120);
		}catch(DuplicateProductException d1) {
			System.out.println(d1.toString());
		}catch(InvalidInputException i1) {
			System.out.println(i1.getMessage());
		}
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream ( new FileOutputStream("out.txt")); 
			out.writeObject(d); 
		}
		finally {
			out.close();
		}
		
		ObjectInputStream in = null; 
		database d1=null;
		try {            
			in =  new ObjectInputStream (new FileInputStream("out.txt")); 
			d1 = (database) in.readObject();
		} finally { 
			in.close(); 
		} 
		assertEquals(d1,d);
	}
	
	@Test
	public void testCustomercart() throws IOException,ClassNotFoundException,outofstockexception{
		database d = new database();
		try {
			d.inscategorytest("Electronics > Smartphones > Android", "Oneplus",10,500);
			d.inscategorytest("Electronics > Kitchen", "Microwave",30,120);
		}catch(DuplicateProductException d1) {
			System.out.println(d1.toString());
		}catch(InvalidInputException i1) {
			System.out.println(i1.getMessage());
		}
		customer c = new customer("user");
		product p=null;
		try {
			p = d.searchprod("Oneplus");
			c.addproducttest(p,1);
		}catch(productnotfound p1) {
			System.out.println(p1.toString());
		}
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("in.txt"));
			out.writeObject(c);
		}finally {
			out.close();
		}
		ObjectInputStream in = null; 
		customer c1=null;
		try {            
			in =  new ObjectInputStream (new FileInputStream("in.txt")); 
			c1 = (customer) in.readObject();
		} finally { 
			in.close(); 
		} 
//		System.out.println(c + "\n" + c1);
		assertEquals(c,c1);
	}
}
