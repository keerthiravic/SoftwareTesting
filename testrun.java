package saucedemo.swaglabs;

import org.testng.TestNG;

public class testrun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestNG testng = new TestNG();
		 testng.setTestClasses(new Class[]{loginPage.class,addProduct.class,priceInCart.class,});
		 testng.run();
	}

}
