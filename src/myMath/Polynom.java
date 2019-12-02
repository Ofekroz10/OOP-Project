package myMath;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
/**
 * the class represent polynom as a collection of monoms.
 * we implement polynom with linkedList.
 * @author Ofek and Dvir
 *
 */
public class Polynom implements Polynom_able{

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		this.monoms = new LinkedList<Monom>();
		//System.out.println("This is the monoms " + this.monoms.toString());
		this.monoms.clear();
		this.monoms.add(new Monom(0,0));
		
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4x^3-34x", "3x-10x^5"},correct string is correct monoms with the symbols '+' for
	 *  positive monom (do need to write + in the first monom), or minus(-) to represent negetive monom.
	 * @param s: is a string represents a Polynom
	 */
	
	private LinkedList<Monom> monoms;
	
	public Polynom(String s) { 
		
		LinkedList<String> monoms = getMonoms(s);
		LinkedList<Monom> monoms1 = new LinkedList<Monom>();
		Polynom p = new Polynom();
		
		for (String mon : monoms) {
			try
			{
				
				Monom m= new Monom(mon);
				p.add(m);
				
				monoms1.add(m);
				
			}
			catch(Exception e)
			{
				throw e;
			}
		}
		
		
		this.monoms = p.monoms;
	}
	/**
	 * 
	 * @param correct string that represent polynom
	 * @return linkedList of monoms.
	 */
	private LinkedList<String> getMonoms(String s)
	{
		return splitBy2chars(s);
		
	}

/**
 * return linkedList of monoms, do split by '+' or '-'
 * @param s represent correct polynom
 * @return return linkedList of string that each item is correct monom.
 */
	private LinkedList<String> splitBy2chars(String s)
	{
		int startIndex = 0;
		String temp = new String("");
		LinkedList<String> list = new LinkedList<String>();
		for(int i=0; i<s.length();i++)
		{
			if(i<1)
			{
				temp += s.charAt(i);
			}
			else
			{
				if(s.charAt(i)== '-' || s.charAt(i)=='+')
				{
					list.add(temp);
					startIndex = i;
					
					temp = new String(String.valueOf(s.charAt(i)));
				}
				else
					temp += s.charAt(i);
			}
			
		}
		list.add(temp);
		return list;
	}
	
	@Override
	/**
	 * return double that represent polynom(x)
	 */
	public double f(double x) {
		double value =0.0;
		for (Monom monom : monoms) {
			value += monom.f(x);
		}
		return value;
	}
	/**
	 * add p1 monoms to this polynom 
	 */
	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext())
		{
			this.add(it.next());
			
		}
		
	}
	/**
	 * add m1 to this polynom , include union monoms with equals powers.
	 */
	@Override
	public void add(Monom m1) {
		boolean added = false;
		//System.out.println("we add "+this.monoms + " plus "+ m1);

		for (int i=0; i<monoms.size();i++) {
			Monom monom = monoms.get(i);
			if(monom.get_power()== m1.get_power())
			{
				//System.out.println("("+monom +")" + "+" +"("+ m1+") "+"=");
				monom.add(m1);
				//System.out.print(monom);
				//System.out.println(monoms);
				added = true;
				if(monom.get_coefficient()==0)
				{
					monoms.remove(i);
				}
				
				break;
				
			}
		}
		if(!added)
		{
			if(m1.get_coefficient()!=0)
				this.monoms.add(m1);
		}	
		//System.out.println("total "+monoms.size());
	}

	@Override
	/**
	 * substract from this polynom p1, by adding the nagative of p1 to this polynom.
	 */
		public void substract(Polynom_able p1) 
		{
			Polynom_able copyP = (Polynom_able) p1.copy();
			Iterator<Monom> it= copyP.iteretor();
			Polynom empty = new Polynom("0");
			while(it.hasNext())
			{
				
				Monom negetive = it.next();
				negetive.nagative();
				empty.add(negetive);
			}
		
			this.add(empty);
			
		}
		
	@Override
	/**
	 * Do this polynom * p1 :
	 * by mult polynom's monoms, in p1 monoms, and then add to empty polynom all the monoms of the mult result.
	 */
	public void multiply(Polynom_able p1) {

		Iterator<Monom> it = p1.iteretor();
		if(p1.isZero())
		{
			this.monoms.clear();
			return;
		}
		if(this.isZero())
		{
			return;
		}
		LinkedList<Monom> lst = new LinkedList<Monom>();
		int index= 0;
		while(it.hasNext())
		{
			Monom cur = it.next();
			for (Monom monom : monoms) {
				Monom m = new Monom(monom);
				m.multipy(cur);
				lst.add(m);
			}
		}
		Polynom p = this.createPolynomFromList(lst);
		this.monoms.clear();
		
		
		for (Monom monom : p.monoms) {
			this.monoms.add(monom);
			
		}
		
	}

	
	
	@Override
	/**
	 * return true if this.polynom -p1(substract) is <= EPSILON
	 */
	public boolean equals(Object p1) {
		if(p1 == this)
			return true;
		if(p1 instanceof Monom)
		{
			Polynom p2 = new Polynom(p1.toString());
			Polynom_able copy = this.copy();
			return copy.equals(p2);
		}
		if((p1 instanceof Polynom_able) == false)
			return false;
		Polynom_able copy=this.copy();
		Polynom_able p2 = (Polynom_able)p1;
		//System.out.println("Before sub " + copy);
		copy.substract(p2);
		
		Iterator<Monom> it = copy.iteretor();
		while(it.hasNext())
		{
			//System.out.println(".");
			Monom cur = it.next();
			//System.out.println(cur);
			if(!cur.equals(Monom.ZERO))
				return false;
		}
		return true;
		
	}
	
	/**
	 * return true if all the monoms of the polynom are zero
	 */
	@Override
	public boolean isZero() {
		for (Monom monom : monoms) {
			if(!monom.isZero())
				return false;
				
		}
		return true;
	}
	/**
	 * return the x cordination that polynom(x) is zero in this interval [x0,x1]
	 * based on Intermediate value theorem
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		
		double fa, fb, middle, fmiddle,a,b;
		a = x0;
		b = x1;
		fa = this.f(a);
		fb = this.f(b);
		middle = (a+b)/2;
		fmiddle = this.f(middle);
		if(fmiddle ==0 || Math.abs(fmiddle)<= eps)
			return middle;
		else
		{
			if(fmiddle <0)
			{
				if(fa>0)
				{
					return root(a,middle,eps);
				}
				else if(fb>0)
				{
					return root(middle,b,eps);
				}
				throw new ArithmeticException("There is no root between x0 to x1");
			}
			else
			{
				if(fa<0)
				{
					return root(a,middle,eps);
				}
				else if(fb<0)
				{
					return root(middle,b,eps);
				}
				throw new ArithmeticException("There is no root between x0 to x1");
			}
		}
		
	}

	@Override
	/**
	 * return clone to polynom, deep copy.
	 */
	public Polynom_able copy() {
		Polynom p = new Polynom();
		p.monoms.clear();
		for (Monom monom : monoms) {
			p.monoms.add(new Monom(monom.toString()));
		}
		return p;
	}

	@Override
	/**
	 * return the derivative of the polynom
	 */
	public Polynom_able derivative() {
		Polynom p = new Polynom();
		LinkedList<Monom> lst= new LinkedList<Monom>();
		p.monoms.clear();
		for (Monom monom : monoms) {
			Monom curDerivative = monom.derivative();
			lst.add(curDerivative);
		}
		Polynom nig= this.createPolynomFromList(lst);
		return nig;
	}

	@Override
	/**
	 * calculate the area in the interval [x0,x1] by riman's sum.
	 * return avrage of the upper sum and lower.
	 */
	public double area(double x0, double x1, double eps) {
		double delta= eps;
		double sum =0;
		
		for(double i = x0;i<=x1; i+=delta)
		{
			double max = f(i+delta);
			double min = f(i);
			double hight = delta;
			if(min>0&&max>0)
			{
				sum += delta *(min+max)/2;
			}
			else if(max == 0 && min>0)
			{
				sum+= (delta*min)/2;
			}
			else if(min == 0 && max >0)
			{
				sum+= (delta*max)/2;
			}
			else if(min<0 && max >=0)
			{
				//min area equal to 0
				double x = this.root(i, i+delta, Monom.EPSILON);
				double rohav = (i+delta)-x;
				sum+= (rohav * max)/2;
			}
			else if(min>=0 && max <0)
			{
				double x = this.root(i, i+delta, Monom.EPSILON);
				double rohav = x-i;
				sum+= (rohav*min)/2;
				
				//max area equal to 0
			}
			
			
		}
		return sum;
	}
	
	@Override
	/**
	 * return monoms.iterator()
	 */
	public Iterator<Monom> iteretor() {
		Iterator<Monom> it = this.monoms.iterator();
		return it;
	}
	@Override
	/**
	 * mult monom in all the polynom's monoms.
	 */
	public void multiply(Monom m1) {
		for (Monom monom : monoms) {
			monom.multipy(m1);
		}
		
	}
	@Override
	/**
	 * return string that represent a polynom, from the biggest power to low. 
	 */
	public String toString()
	{
		if(this.monoms.size()==0)
		{
			return Monom.ZERO.toString();
		}
		this.monoms.sort(Monom.getComp());
		String s = new String("");
		int index=0;
		for (Monom monom : monoms) {
			if(monom.get_coefficient()!=0)
			{
			if(monom.toString().charAt(0)!='-'&&index>0)
			{
				s+= "+"+monom.toString();
			}
			else
				s+= monom.toString();
			
			index++;
		}
		}
		if(s.isEmpty())
			return Monom.ZERO.toString();
		return s;
	}
	/**
	 * 
	 * @param lst linkedlist
	 * @return new polynom from this linkedlist.
	 */
	private Polynom createPolynomFromList(LinkedList<Monom>lst)
	{
		String s =new String("");
		int index=0;
		for (Monom monom : lst) {
			if(monom.toString().charAt(0)!='-'&&index>0)
			{
				s+= "+"+monom.toString();
			}
			else
				s+= monom.toString();
			
			index++;
		}
		return new Polynom(s);
	}
	@Override
	public function initFromString(String s) {
		function p = new Polynom(s);
		return p;
	}

	
	
	
}
