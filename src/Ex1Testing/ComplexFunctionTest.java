package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;
import myMath.ComplexFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import myMath.Polynom;
import myMath.Monom;

class ComplexFunctionTest {
	private  ComplexFunction p1,p2,zero;

	/*
	 * 
	 */
	@BeforeEach
	void init() {
		zero =new  ComplexFunction(new Polynom());
		p1 = new ComplexFunction(new Polynom("4x^3"));
		p2 = new ComplexFunction(new Polynom("x^3+x-3"));

	}
	/*
	 * test for multiply fun
	 */

	@Test
	void multiplyTest() {
		p1.mul(p2);

		Polynom expected1 = new Polynom("4x^3");
		expected1.multiply(new Polynom("x^3+x-3"));
		for(int i=1; i<20;i++)
			assertEquals(expected1.f(i),p1.f(i));
		
	}
	/*
	 * test for f chek the value of f(x)
	 */
	@Test
	void testFunction() {
		double expected = -1.0;
		double t = p2.f(1);
		assertEquals(expected, t,"A test for f(x)");
	}
	/*
	 * test for add fun
	 */

	@Test
	void plusTest() {
		Polynom expected1 = new Polynom("4.0x^3");
		Polynom expected2 = new Polynom("1.0x^3+1.0x^1-3.0x^0");
		expected1.add(expected2);
		p1.plus(p2);
		for(int i=0; i<20;i++)
			assertEquals(expected1.f(i),p1.f(i));
	}


	/*
	 * test for copy fun
	 */

	@Test	
	void copyTest() {
		ComplexFunction c1 = new ComplexFunction("plus",new Monom(2,4),new Monom(1,1));
		ComplexFunction p4 = (ComplexFunction) c1.copy();
		System.out.println(p4);
		assertTrue(p4.equals(c1));
	}
	/*
	 * test for comp fun
	 */

	@Test
	void compTest() {
		double expected =0 ;
		p1.comp(zero);

		assertTrue(p1.f(1)==expected);

	}
	/*
	 * test for max fun
	 */

	@Test
	void maxTest() {
		double expected = 4;
		p1.max(p2);
		assertTrue(p1.f(1)==expected);
	}
	void minTest() {
		double expected = -1;
		p1.min(p2);
		assertTrue(p1.f(1)==expected);
	}
	/*
	 * test for divid fun
	 */

	@Test 
	void divTest() {

		double expected =4 ;
		p1.div(new ComplexFunction(new Monom("1")));

		assertTrue(p1.f(1)==expected);
	}
	/*
	 * test for setleft 
	 */

	@Test
	void leftTest() {
		Polynom t = new Polynom("4x^3");
		assertTrue(t.equals(new Polynom(p1.left().toString())));
	}
	/*
	 * test for set right fun
	 */

	@Test
	void rightTest() {

		assertTrue(this.p1.right()==null);
	}
	/*
	 * test for f(x)
	 */

	@Test
	void fTest() {
		double expected =4.0;
		assertTrue(expected==p1.f(1));
	}
	/*
	 * test for comp fun
	 */

	@Test
	void compTest1() {
		double expected =4.0;
		p1.comp(new Monom("x"));
		assertTrue(expected==p1.f(1));
	}

}
