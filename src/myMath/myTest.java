package myMath;

import java.io.IOException;
import java.util.LinkedList;

public class myTest {

	public static void main(String[] args) throws IOException {
		/*
		Polynom p1 = new Polynom("3x^7+25x^2-22");
		Polynom p2 = new Polynom("-10x^7+25x^3+10");
		System.out.println("p1= "+p1.toString());
		System.out.println("p2= "+p2.toString());
		Polynom_able p3 = p1.copy();
		p1.substract(p2);
		System.out.println("("+p3.toString()+") - ("+p2.toString() +") = " +p1.toString());
		System.out.println("Before mult: "+p1);
		Polynom_able mult = p1.copy();
		p1.multiply(new Monom("3x^2"));
		System.out.println("After mult: "+p1.toString());
		p1 = new Polynom("2x^5-3x");
		System.out.println("Before mult: "+p1);
		p1.multiply(new Polynom("-8x^4+2"));
		System.out.println("After mult "+p1.toString());
		Polynom p4 = new Polynom("3x^2+220x+2");
		System.out.println("The root between [-90,-20] is: " +p4.root(-90,-20,Monom.EPSILON));
		p4 = new Polynom(new String("3x^2+4-2x^3"));
		System.out.println("The area between [-10,7] is: "+p4.area(-2,5.4,0.001));
		System.out.println("p4: "+p4.toString());
		Polynom p5 = new Polynom(new String("-3x^2+4-2x^3"));
		System.out.println("p5: "+p5.toString());
		System.out.println("Is p4 equals to p5? "+p4.equals(p5));
		System.out.println("p5 equals to p5? "+p5.equals(new Polynom(p5.toString())));
		System.out.println("Now show wrong using: ");
		try
		{
			System.out.println("3x^7+99x^7+2y");
			Polynom p6 = new Polynom("3x^7+99x^7+2y");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		try
		{
			System.out.println("3x^7+99x^7*2");
			Polynom p6 = new Polynom("3x^7+99x^7*2");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		try
		{
			System.out.println("3X");
			Polynom p6 = new Polynom("3X");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		 */
		//constractor of 1
		ComplexFunction c = new ComplexFunction("plus",new Monom("2x"),new Monom("4x"));
		
		c.plus(new ComplexFunction("mult",new Polynom("x"),new Monom("x")));
		c.plus(new Monom("4x"));
		c.mul(new Monom("2"));
		//c.max(new Monom("26x"));
		ComplexFunction d = new ComplexFunction("plus",new Monom("2x"),new Monom("4x"));
		d.plus(new Monom("2x"));
		c.mul(d);
		c.div(new Monom("2x"));
		function f = c.copy();
		double x =c.f(2);
		
		System.out.println(x);
		String a = c.toString();
		System.out.println(a);
		/*Functions_GUI gui = new Functions_GUI();
		gui.add(c);
		gui.add(new Monom(3,4));
		gui.saveToFile("object.txt");
		System.out.println(gui.collection);
		System.out.println("---");
		gui = new Functions_GUI();
		gui.initFromFile("object.txt");
		System.out.println(gui.collection);
		System.out.println(f.f(2));
		*/
		/*ComplexFunction1 fun = new ComplexFunction1("plus",new Monom("3x^2"),new Monom("2x"));
		fun.plus(new Monom("2x"));
		System.out.println(fun);*/
		ComplexFunction c1 = new ComplexFunction("plus",new Monom("2x"),new Monom("4x"));
	
		c1.plus(new ComplexFunction("mult",new Polynom("x"),new Monom("x")));
		c1.plus(new Monom("4x"));
		c1.mul(new Monom("2"));
		//c.max(new Monom("26x"));
		ComplexFunction d1 = new ComplexFunction("plus",new Monom("2x"),new Monom("4x"));
		d1.plus(new Monom("2x"));
		c1.mul(d1);
		c1.div(new Monom("2x"));
		Polynom p = new Polynom("-1.0x^4+2.4x^2+3.1x^0");
		double x1 =c1.f(2);
		
		Functions_GUI gui = new Functions_GUI();
		//gui.add(new Polynom("x^2"));
		gui.add(c1.initFromString("plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0)"));
		gui.add(c1.initFromString("plus(div(+1.0x+1.0,mult(mult(+1.0x+3.0,+1.0x-2.0),+1.0x-4.0)),2.0)")); 
		gui.add(c1.initFromString("div(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)"));
		gui.add(c1.initFromString("-1.0x^4+2.4x^2+3.1"));
		gui.add(c1.initFromString("max(max(max(max(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5" + 
				"-1.2999999999999998x+5.0),plus(div(+1.0x+1.0,mult(mult(+1.0x+3.0,+1.0x-2.0),+1.0x-4.0)),2.0)),div(plus(-" + 
				"1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)),-1.0x^4+2.4x^2" + 
				"+3.1),+0.1x^5-1.2999999999999998x+5.0)"));
		gui.add(c1.initFromString("min(min(min(min(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-" + 
				"1.2999999999999998x+5.0),plus(div(+1.0x+1.0,mult(mult(+1.0x+3.0,+1.0x-2.0),+1.0x-4.0)),2.0)),div(plus(-" + 
				"1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)),-1.0x^4+2.4x^2" + 
				"+3.1),+0.1x^5-1.2999999999999998x+5.0)"));
		//gui.add(c1.initFromString("plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0)"));
		//gui.add(new Monom(3,2));
		//gui.add(new ComplexFunction(new Polynom("2x")));*/
		gui.saveToFile("object.txt");
		gui.initFromFile("object.txt");
		System.out.println(gui.collection);
		System.out.println(x1);
		String a1 = c1.toString();
		System.out.println(a1);
		gui.drawFunctions("object.json");
		
		
	}

}
