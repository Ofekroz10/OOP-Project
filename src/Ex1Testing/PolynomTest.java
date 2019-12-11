package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myMath.Polynom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class PolynomTest {
	public static final double EPSILON = 0.0001;
	 private Polynom p1,p2,zero ;
	
	@BeforeEach
	void init() {
		zero = new Polynom();
		p1 = new Polynom("4x^3-2x+1");
		p2 = new Polynom("x^3+x-3");
		
	}
	/*
	 * A test for the default polynom constructor
	 */
	@Test
	void test_For_Default_PolynomConstructor(){
		String expected = "0.0x^0";
		String s = zero.toString();
		int x = s.compareTo(expected);
		System.out.println(x);
		if(x==0) {
			assertTrue(true,"A test for the default polynom constructor");
		}else {
			assertTrue(false,"A test for the default polynom constructor");
		}
	}
	/*
	 *  test for the String polynom constructor
	 */
	@Test
	void test_For_String_PolynomConstructor() {
		
		String expected = "4.0x^3-2.0x^1+1.0x^0";
		String s1 = p1.toString();
		assertEquals(expected, s1,"A test for the String polynom constructor");
	}
	/*
	 * A test for f(x)
	 */
	@Test
	void testFunction() {
		double expected = -1.0;
		double t = p2.f(1);
		assertEquals(expected, t,"A test for f(x)");
	}
	/*
	 * A test for add polynoms"
	 */
	@Test
	void addTest() {
		Polynom expected = new Polynom("5.0x^3-1.0x^1-2.0x^0");
		p1.add(p2);
		boolean equal = p1.equals(expected);
		assertTrue(equal,"A test for add polynoms");
		
	}
	/*
	 * test to substract function
	 */
	@Test
	void substractTest() {
		Polynom expected = new Polynom("3x^3-3x+4");
		p1.substract(p2);
		assertTrue(p1.equals(expected));
	}
	/*
	 * test for multyply function
	 */
	@Test
	void multiplyTest() {
		
		
		Polynom expected = new Polynom("4x^6+2x^4-11x^3-2x^2+7x-3");
		p1.multiply(p2);
		
		boolean equal = p1.equals(expected);
		assertTrue(equal);
	}
	
	/*
	 * test for equals test
	 */
	@Test
	void equalsTest() {
		String expected = "4.0x^3-2.0x^1+1.0x^0";
		assertTrue(p1.toString().compareTo(expected)==0);
	}
	/*
	 * chek for zero constractur
	 */
	@Test
	void isZeroTest() {
		String expected = "0.0x^0";
		
		assertTrue(zero.toString().compareTo(expected)==0);
	}
	/*
	 * test for root function
	 */
	@Test
	void rootTest() {
		Polynom p3 = new Polynom("x^2-x");
		double expected = 0;
		assertEquals(expected, p3.root(-0.5,0.5, EPSILON));
	}
	/*
	 * test for copy function
	 */
	@Test
	void copyTest() {
		Polynom p3 = (Polynom) p2.copy();
		assertTrue(p2.equals(p3));
	}
	/*
	 * test for ferivative 
	 */
	@Test
	void derivativeTest() {
		Polynom expected = new Polynom("3x^2+1");
		assertTrue(expected.equals(p2.derivative()));
	}
	/*
	 * test to area
	 */
	@Test
	void areaTest() {
		double expected = 18.11208429422139;
		assertEquals(expected, p2.area(1, 3, EPSILON));
	}
	/*
	 * test to string function
	 */
	@Test
	void toStringTest() {
		String expected = "4.0x^3-2.0x^1+1.0x^0";
		String s = p1.toString();
		if(expected.compareTo(s)==0) {
			assertTrue(true);
		}else {
			assertTrue(false);
		}	
	}
	
}
