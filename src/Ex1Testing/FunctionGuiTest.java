package Ex1Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonIOException;

import Ex1.*;

class FunctionGuiTest {
	private Functions_GUI fg;
	private ComplexFunction c;

	@BeforeEach
	void init() {
		
		fg = new Functions_GUI();
		c= new ComplexFunction(new Monom("2x"));
	}

	/*
	 * test for load file 
	 */
	@Test
	void initFromFileTest() throws IOException {
		fg.initFromFile("FuncsTest.txt");
		LinkedList<function> lst = new LinkedList<function>();
		lst.add(c.initFromString("plus(-1.0x^4+2.4x^2+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0)"));
		lst.add(c.initFromString("plus(div(1.0x^1+1.0x^0,mult(mult(1.0x^1+3.0x^0,1.0x^1-2.0x^0),1.0x^1-4.0x^0)),2.0x^0)"));
		lst.add(c.initFromString("div(plus(-1.0x^4+2.4x^2+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0),-1.0x^4+2.4x^2+3.1x^0)"));
		lst.add(c.initFromString("-1.0x^4+2.4x^2+3.1x^0"));
		Iterator<function> it = fg.iterator();
		int i =0;
		
		while(it.hasNext())
		{
			boolean b = (lst.get(i).equals(it.next()));
			assertTrue(b);
			i++;
		}
		
	}
	/*
	 * test for read json
	 */
	@Test
	void readJsonTest() throws JsonIOException, IOException
	{
		assertTrue(fg.fromJson("jsonTest.json"));
		GuiParams testP = new GuiParams(1000,600,400,new Range(-10,10),new Range(-15,15));
		assertEquals(fg.getGUIParams(),testP);
	}
	

}
