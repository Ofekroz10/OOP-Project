package Ex1Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.Monom;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MonomTest {

	private Monom m1,m2;
	
	@BeforeEach
	void init() {
	 m1 = new Monom(2,1);
	 m2 = new Monom(-1,1);
	}
	/*
	 *"A constructor test that gets a string"
	 */
	@Test
	void constructorGetString() {
		Monom m3 = new Monom("2.0x^1");
		boolean equal = m1.equals(m3);
		assertTrue(equal,"A constructor test that gets a string");
	}
	/*
	 * Test Add with two Monoms
	 */
	@Test
	void addTest() {
		Monom expected = new Monom(1,1);
		m1.add(m2);
		assertEquals(expected, m1,"Test Add with two Monoms");
	}
	/*
	 * Test multipy with two Monoms
	 */
	@Test
	void multipyTest() {
		Monom expected = new Monom(-2,2);
		m1.multipy(m2);
		assertEquals(expected, m1,"Test multipy with two Monoms");
		
	}
	/*
	 *Test toString fun 
	 */
	@Test
	void toStringTest() {
		String expected="2.0x^1";
		String m = m1.toString();
		assertEquals(expected, m,"Test toString fun");	
	}
	/*
	 * test for equals
	 */
	@Test
	void equals() {
		Monom m3 = new Monom(m1);
		boolean equal = m1.equals(m3);
		assertTrue(equal);
	}
	
	
	
	
	

}
