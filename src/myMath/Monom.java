
package myMath;


import java.awt.List;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	private int indexNext;
	private boolean onlyNum;
	/**
	 * Constractor, create new Monom.
	 * @param a is the coefficient.
	 * @param b is the power.
	 */
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	/**
	 * create new monom by deep copy of ot.
	 * @param ot is the original monom.
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	/**
	 * 
	 * @return return the coefficient.
	 */
	public double get_coefficient() {
		return this._coefficient;
	}
	/**
	 * 
	 * @return return the power.
	 */
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	/**
	 * @return return the y cordination of x in f.
	 */
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	/**
	 * 
	 * @return true if the monom is zero by checking the coefficient, else return false.
	 */
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	
	/**
	 * create new monom from string.
	 * @param s represent the monom. correct form is first the coefficient, then the character
	 * 'x' only lower letter ('X' is not correct). then the symbol '^' and then the value of 
	 * the power. there is no spaces in the expression. examples:
	 * 4x^2
	 * 9x^0
	 * if you write only number its means that the power is . for example: 5x^0 = 5,
	 * you can write only number.
	 * if you write 9x its means that the power of the monom is 1. 9x= 9x^1.
	 * the expression do not include complicated expression like: (9+4)x^3 or 9*4x^3 or 9x^(9+8)...
	 * if you write x its equals to 1x^1, -x = -1x^1
	 * 
	 */
	public Monom(String s) {
		//a*x^b
		s=s.replace(" ","");
		char[] array = s.toCharArray();
		
		double x = getMekadem(array);
		
		this.set_coefficient(getMekadem(array));
		
		if(indexNext < array.length)
		{
		if(array[indexNext] != 'x')
		{throw new RuntimeException("The expression is not correct");} 
		indexNext++;
		if(indexNext == array.length)
		{
			this.set_power(1);
		}
		else
		{
		if(array[indexNext] != '^')
		{throw new RuntimeException("The expression is not correct");} 
		indexNext++;
		//System.out.println("start" + indexNext);
		String  powStr = s.substring(indexNext);
		 
		try
		{
			int pow1 = Integer.parseInt(powStr);
			this.set_power(pow1);
		
		}
		catch(Exception e)
		{
			
			{throw new RuntimeException("The pow is not correct");}} 
		}
		}
		else
			this.set_power(0);
	}
	/**
	 * add m.coefficient to this coefficient if both of power are the same
	 * @param m the other monom
	 */
	public void add(Monom m) 
	{
		if(m.get_power()==get_power())
		{
			this.set_coefficient(this.get_coefficient()+m.get_coefficient());
		}
	}
	/**
	 * add d.power to this power. 
	 * mult d.coefficient to this coefficient.
	 * @param d
	 */
	public void multipy(Monom d)
	{
		double nMekadem = d.get_coefficient() *this.get_coefficient();
		int newPow = d.get_power()+this.get_power();
		this.set_coefficient(nMekadem);
		this.set_power(newPow);
	}
	/**
	 * return string from this form: coefficient+"x^"+power
	 */
	public String toString() {
		String ans = "";
		ans += this.get_coefficient() + "x^" + this.get_power();
		return ans;
	}
	
	@Override
	/**
	 * return true if(this monom - o)is <= EPSILON
	 */
	public boolean equals(Object o)
	{
		if(o ==this)
			return true;
		if(o instanceof Polynom_able)
		{
			Polynom_able asPol =(Polynom_able)o;
			return asPol.equals(new Polynom(this.toString()));
		}
			
		
		if(!(o instanceof Monom))
			return false;
		Monom m= (Monom)o;
		Monom temp = new Monom(this.get_coefficient(),this.get_power());
		m.nagative();
		temp.add(m);
		if(Math.abs(temp.get_coefficient())<= EPSILON)
			return true;
		
		return false;
	}
	/**
	 * change the coefficient to the negetive. -x -> x, -5->5, 9 -> -9. 
	 */
	public void nagative()
	{
		this.set_coefficient(this.get_coefficient()*(-1));
	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************
	
	/**
	 * return new array of char, but only the items from index start to end.
	 * @param array
	 * @param start the index to start the sub.
	 * @param end the index to end the sub.
	 * @return return new array of char, but only the items from index start to end.
	 */
	private char[] subArray(char[] array,int start,int end)
	{
		int index = 0;
		if(start == end)
		{
			return new char[] {array[start]};
		}
		int size = end-start;
		char[] arr= new char[size];
		while(start<end)
		{
			arr[index] = array[start];
			start++;
			index++;
		}
		return  arr;
		
	}
	/**
	 * get array of chars, return the coefficient of x
	 * @param array
	 * @return double that represent the coefficient of x
	 */
	private double getMekadem(char[] array)
	{
		double makadem =1;
		int positive = array[0] != '-'? 1 : -1;
		int indexStart = positive == 1 ? 0: 1;
		makadem = positive;
		if(array[0]=='+')
			indexStart= 1;
		this.indexNext = indexStart;
		boolean onlyNum = false;
		
		if(array[indexStart] == 'x')
			return (double)(makadem);
		int endIndex = indexStart;
		
		while(array[endIndex]!='x')
		{
			endIndex++;
			onlyNum = endIndex == array.length ? true : false;
			if(onlyNum)
			{
				this.onlyNum = true;
				break;
			}
			
		}
		
		char[] mekadem =  subArray(array,indexStart,endIndex);
		
		this.indexNext =endIndex;
		if(mekadem[0] == '-' || mekadem[0] == '+')
		{throw new RuntimeException("Incorrect coeficient");}
			
		try {
			return (Double.parseDouble((String.valueOf(mekadem)))*makadem);
		}
		catch (Exception e)
		{
			 {throw new RuntimeException("Incorrect coeficient");}
		}
	
	}
	/**
	 * 
	 * @param set the coefficient to a
	 */
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	/**
	 * 
	 * @param set the power to p
	 */
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	/**
	 * 
	 * @return new monon that equals(logicly) to 0x^0
	 */
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
	@Override
	public function initFromString(String s) {
		Monom m = new Monom(s);
		return m; 
	}
	@Override
	public function copy() {
		function m = new Monom(this.get_coefficient(),this.get_power());
		return m;
	}

	
	
}
