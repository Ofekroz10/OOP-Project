package myMath;



public class ComplexFunction implements complex_function
{

	private function func1;
	private function func2;
	private Operation op;
	private String opString;
	
	public ComplexFunction(function f1)
	{
		this.opString = "none";
		this.func1 = f1;
		this.op = Operation.None;
	}
	
	public ComplexFunction(String action, function f1,function f2) 
	{
		this.opString = action;
		this.func1 = f1;
		this.func2 = f2;

		switch(action) {
			case "plus": op = Operation.Plus;
			break;
			case "mult": op = Operation.Times;
			break;
			case  "div": op = Operation.Divid;
			break;
			case "min" :op = Operation.Min;
			break;
			case "max":op = Operation.Max;
			break;
			case "comp":op = Operation.Comp;
			break;
			case "none":op = Operation.None;
			break;
			default: 
				{op = Operation.Error;
			System.out.println("eror, operation do not exist");
				}
		}
		
	
				
	}
	
	@Override
	public double f(double x)  {
		
		switch(op)
		{
			case Plus:
			{
				double rightSide = this.right().f(x);
				double leftSide = this.left().f(x);
				return rightSide+leftSide;
			}
			case Times:
			{
				double rightSide = this.right().f(x);
				double leftSide = this.left().f(x);
				return rightSide*leftSide;
			}
			case Divid:
			{
				double rightSide = this.right().f(x);
				double leftSide = this.left().f(x);
				return leftSide/rightSide;
			}
			case Min:
			{
				double leftSide = this.left().f(x);
				double rightSide = this.right().f(x);
				return Math.min(leftSide,rightSide);
			}
	
			case Max:
			{
				double leftSide = this.left().f(x);
				double rightSide = this.right().f(x);
				return Math.max(leftSide,rightSide);
			}
			case Comp:
			{
				double rightSide = this.right().f(x);
				double left = this.left().f(rightSide);
				return left;
			}
			case None:
			{
				return this.left().f(x);
			}
			
			default: throw new ArithmeticException("Operation do not exist");
		}
		
	}

	@Override
	public function initFromString(String s) {
		function f1,f2;
		Operation o;
		String oStr=new String("");
		int start = 0;
		
		try {
			
			if(s.length() <4) {
				return new Polynom(s);
			}
			if(s.charAt(3)=='(')
				start = 4;
			else
				start = 5;
			oStr = s.substring(0,start-1);
			System.out.println(s.charAt(start));
			int end_brackes = this.end2brackes(s.substring(start));
			o = this.getO(oStr);
			if(end_brackes==-1)
			{
				System.out.println("CF");
				String onlyBitui = s.substring(start,s.length()-1);
				String[] funcs = onlyBitui.split(",");
				if(funcs.length==1)
				{
					return new ComplexFunction(new Polynom(funcs[0]));
				}
				else {
					return new ComplexFunction(oStr,new Polynom(funcs[0]),new Polynom(funcs[1]));
				}
				
				
			}
			else if(end_brackes!=0)
			{
				end_brackes +=start;
				System.out.println(oStr);
				System.out.println(end_brackes);
				System.out.println("right "+s.substring(end_brackes+2,s.length()-1));
				f2 = initFromString(s.substring(end_brackes+2,s.length()-1));
				System.out.println("left "+s.substring(start,end_brackes+1));
				f1 = initFromString(s.substring(start,end_brackes+1));
				return new ComplexFunction(oStr,f1,f2);
			}
			else
			{
				
				return new Polynom(s);
			}
			
	
		}
		catch(Exception e)
		{
			//maybe its complex function or 
			try
			{
			String[] s2 = new String[2] ;
			int indexFirst = s.substring(start).indexOf(",");
			s2[0] = s.substring(start).substring(0,indexFirst);
			s2[1] = s.substring(start).substring(indexFirst+1,s.substring(start).length()-1);
			f2 = initFromString(s2[1]);
			f1 = initFromString(s2[0]);
			return new ComplexFunction(oStr,f1,f2);
			}
			catch(Exception e1)
			{
				System.out.println(s);
				System.out.println("in correct string from file");
				System.out.println(e1);
				return null;
			}
			
		}
				
	}

	private int end2brackes(String s) {
		int counter = 0;
		boolean seeFirst = false;
		
		for(int i =0; i<s.length();i++)
		{
			if(s.charAt(i)=='(')
			{
				counter++;
				seeFirst=true;
			}
			else if(s.charAt(i)==')')
				counter--;
			
			if(seeFirst&&counter==0)
				return i;
		}
		
		return counter;
	}

	
	private Operation getO(String oStr) {
		switch(oStr) {
		case "plus": return Operation.Plus;
		
		case "mult": return Operation.Times;
		
		case  "div": return Operation.Divid;
		
		case "min" :return Operation.Min;
		
		case "max":return Operation.Max;
		
		case "comp":return Operation.Comp;
		
		case "none":return Operation.None;
		
		default: 
			return Operation.Error;}}
		
			
	
		

	@Override
	public function copy() {
		ComplexFunction c = new ComplexFunction(new String(this.opString),func1.copy(),func2.copy());
		return c;
		
	}

	@Override
	public void plus(function f1) {
		if(func2 == null)
		{
			func2 = f1;
			this.op = Operation.Plus;
			this.opString = "plus";
		}
		else
		{
			function copy =this.copy();
			this.func1  =copy;
			this.func2 = f1;
			this.op = Operation.Plus;
			this.opString = "plus";
		}
		
		
	}

	@Override
	public void mul(function f1) {
		if(func2 == null)
		{
			func2 = f1;
			this.op = Operation.Times;
			this.opString = "mult";
		}
		else
		{
			function copy =this.copy();
			this.func1  =copy;
			this.func2 = f1;
			this.op = Operation.Times;
			this.opString = "mult";
		}
		
	}

	@Override
	public void div(function f1) {
		if(func2 == null)
		{
			func2 = f1;
			this.op = Operation.Divid;
			this.opString = "div";
		}
		else
		{
			function copy =this.copy();
			this.func1  =copy;
			this.func2 = f1;
			this.op = Operation.Divid;
			this.opString = "div";
		}
		
	}

	@Override
	public void max(function f1) {
		if(func2 == null)
		{
			func2 = func1.copy();
			func1 = f1;
			this.op = Operation.Max;
			this.opString = "max";
		}
		else
		{
			function copy =this.copy();
			this.func1  =f1;
			this.func2 = copy;
			this.op = Operation.Max;
			this.opString = "max";
		}
		
	}

	@Override
	public void min(function f1) {
		if(func2 == null)
		{
			func2 = f1;
			this.op = Operation.Min;
			this.opString = "min";
		}
		else
		{
			function copy =this.copy();
			this.func1  =copy;
			this.func2 = f1;
			this.op = Operation.Min;
			this.opString = "min";
		}
		
	}

	@Override
	public void comp(function f1) {
		if(func2 == null)
		{
			func2 = f1;
			this.op = Operation.Comp;
			this.opString = "comp";
		}
		else
		{
			function copy =this.copy();
			this.func1  =copy;
			this.func2 = f1;
			this.op = Operation.Comp;
			this.opString = "comp";
		}
	}

	@Override
	public function left() {
		return func1;
	}

	@Override
	public function right() {
		return func2;
	}

	@Override
	public Operation getOp() {
		return this.op;
	}
	@Override
	public String toString() {

		String s =new String();
		if(func2!= null)
			s+= this.opString+"("+func1.toString()+","+func2.toString()+")";
		else
			s+= this.opString+"("+func1.toString()+")";
		return s;
		
	}
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof ComplexFunction)
		{
			ComplexFunction cf = (ComplexFunction)o;
			if(getOp() == cf.getOp())
			{
				if(cf.getOp()==Operation.Divid || getOp()== Operation.Comp)
				{
					return this.func1.equals(cf.left()) && this.func2.equals(cf.right());
				}
				else
				{
					return( this.func1.equals(cf.left()) && this.func2.equals(cf.right()))||(this.func1.equals(cf.right())&&this.func2.equals(cf.left()));
				}
			}
			else
				return false;
			
		}
		return false;
	}



}
