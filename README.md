<a href="https://imgbb.com/"><img src="https://i.ibb.co/0FtmpKk/Untitled.png" alt="Untitled" border="0"></a>



**Monom**
***


Welcome to Polynom's wiki. This project help you to learn about polynom. You can create function: polynom, monom, and complex function. First, i will explain about monom calss. monoms are the base of polynoms, monom defined in this form:
ax^b , a is real number and b is natural number. You can init monom object by using this constructor: 
`Monom firstMonom = new Monom(3,4);` -> create 3x^4. 
You can also create by string `Monom firstMonom = new Monom("3x")` equals to `Monom firstMonom = new Monom("3x^1");`.
More examples:
`Monom firstMonom = new Monom("-2");` equals to `Monom firstMonom = new Monom("-2x^0");`
equals to `Monom firstMonom = new Monom(2,0);`. **to init a monom using the string constructor, dont use spaces**.

Monom class implements the interface "function" , so you can use the functions: f(x) - return the value of f(x),
initFromString that return monom from string, clone() is deep copy of the monom, and equals. equals function return true if
the subtraction of two monoms less then EPSILON. for example
`Monom firstMonom = new Monom("2x^2");
Monom secondMonom = new Monom("1.999999999999x^2");
boolean eq = firstMonom.equals(secondMonom);` -> eq = ture.
Equals function can compare monom and polynom, 
Equals function able to compare monom and polynom, by using polynom's equals function. (Its allow because a.equals(b) = b.equals(a)). Monom class also contains the add and multiply function. for example
`Monom firstMonom = new Monom("-2x");
Monom secondMonom = new Monom("-2x");
firstMonom.mult(secondMonom);` -> firstMonom = 4x^2





Polynom
***
Polynom class implements by linkedList of monom type. There is a string constructor `Polynom p = new Polynom("2x+3-4x^2+66x-99+10x-45");` -> create the polynom -4.0x^2+78.0x^1-141.0x^0

**To init polynom from string, enter legal monoms, with "+" or "-" between each monom **
polynom implements polynom_able, which means that you can use add, mult, sub,derivative... you can see java doc html to read more about this functions.
an example to use this functions:
```java
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
```
you will get this output:
```java
p1= 3.0x^7+25.0x^2-22.0x^0
p2= -10.0x^7+25.0x^3+10.0x^0
(3.0x^7+25.0x^2-22.0x^0) - (-10.0x^7+25.0x^3+10.0x^0) = 13.0x^7-25.0x^3+25.0x^2-32.0x^0
Before mult: 13.0x^7-25.0x^3+25.0x^2-32.0x^0
After mult: 39.0x^9-75.0x^5+75.0x^4-96.0x^2
Before mult: 2.0x^5-3.0x^1
After mult -16.0x^9+28.0x^5-6.0x^1
```
The equals function return true if this polynom subtract other polynom less then Monon.EPSILON. Equals can compare polynom with monom, by this way: 1. create new polynom from the monom, then return equals of the polynoms.
Polynom class also implement count_function interface. this interface contains two functions: area and root.
Both of them get range(x0,x1), and area return the positive area, by using Riman's sum. for example, for this code:
```java
p4 = new Polynom(new String("3x^2+4-2x^3"));
System.out.println("The area between [-10,7] is: "+p4.area(-2,5.4,0.001));
``` 
that represent this area
<a href="https://ibb.co/XtYk0cf"><img src="https://i.ibb.co/2dZYVG0/Untitled.png" alt="Untitled" border="0"></a>
and the sum is 32
The second function is root, return the root (f(x) =0) between the Xrange. The algorithm is Intermediate value theorem.The func throw ArithmeticException if there is no root in this range.
```java
Polynom p4 = new Polynom("3x^2+220x+2");
System.out.println("The root between [-90,-20] is: " +p4.root(-90,-20,Monom.EPSILON));
```
The result is -73.32424129723222

ComplexFunction
***
The lest type of function(this class also implements function interface). Complex function represent 2 functions(monom,polynom or complex funcftion) with operation. The operations are: plus, mult, div, comp,none,min,max.
Operation enum contains this values. 
**Operation**
Plus: plus(f1,f2) = f1+f2
Times: mult(f1,f2) = f1*f2
Divide: div(f1,f2) = f1/f2
Comp: f1(f2)
Min: Min(f1,f2)
Max: Max(f1,f2)
None: f1
Eror: illegal parameters from constructor.
The implementation of this class is recursive method. First create complex function object, you can use 2 constructors:
```java
ComplexFunction d1 = new ComplexFunction("plus",new Monom("2x"),new Monom("4x")); //plus(2x,4x)
``` 
```java
ComplexFunction d1 = new ComplexFunction(Operation.Plus,new Monom("2x"),new Monom("4x")); //plus(2x,4x)
``` 
```java
ComplexFunction d1 = new ComplexFunction(new Monom("2x")); //none(2x)
``` 
Complex function can be more complex, like in this example: 
```java
plus(-1.0x^4+2.4x^2+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0)
plus(div(1.0x^1+1.0x^0,mult(mult(1.0x^1+3.0x^0,1.0x^1-2.0x^0),1.0x^1-4.0x^0)),2.0x^0)
div(plus(-1.0x^4+2.4x^2+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0),-1.0x^4+2.4x^2+3.1x^0)
-1.0x^4+2.4x^2+3.1x^0
max(max(max(max(plus(-1.0x^4+2.4x^2+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0),plus(div(1.0x^1+1.0x^0,mult(mult(1.0x^1+3.0x^0,1.0x^1-2.0x^0),1.0x^1-4.0x^0)),2.0x^0)),div(plus(-1.0x^4+2.4x^2+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0),-1.0x^4+2.4x^2+3.1x^0)),-1.0x^4+2.4x^2+3.1x^0),0.1x^5-1.2999999999999998x^1+5.0x^0)
min(min(min(min(plus(-1.0x^4+2.4x^2+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0),plus(div(1.0x^1+1.0x^0,mult(mult(1.0x^1+3.0x^0,1.0x^1-2.0x^0),1.0x^1-4.0x^0)),2.0x^0)),div(plus(-1.0x^4+2.4x^2+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0),-1.0x^4+2.4x^2+3.1x^0)),-1.0x^4+2.4x^2+3.1x^0),0.1x^5-1.2999999999999998x^1+5.0x^0)
```
**You can use initFromString to create complex function from string, use this operations: plus, mult, div, min, max,comp, none.**
The class complex function contains the methods: plus,mult,div... the algorithm is recursive:
if f2 is null, make f2 to be the parameter of the function.
else make f1 to be the clone of this complex function, and f2 to the parameter. 
Both of cases, the operation changed to the value of the function.
The function left() and right() return the left and the right function of the complex function.
f(X)- return the value of the complex function while x is the parameter of the function f. To do this, I calculate the left side in the right side recursively by calling f(left).f(x) and f(right).f(x), and then apply the operation between two numbers. The function comp is unusual , first I calculate the right side in x, and then calculate the left side by using f(rightSideSum).
For example this code create:
```java
ComplexFunction c1 = new ComplexFunction("plus",new Monom("2x"),new Monom("4x")); // plus(2x,4x)
c1.plus(new ComplexFunction("mult",new Polynom("x"),new Monom("x"))); //plus(plus(2x,4x),mult(x,x))
c1.plus(new Monom("4x")); //plus(plus(plus(2x,4x),mult(x,x)),4x)
c1.mul(new Monom("2"));//mult(plus(plus(plus(2x,4x),mult(x,x)),4x),2)
ComplexFunction d1 = new ComplexFunction("plus",new Monom("2x"),new Monom("4x")); //plus(2x,4x)
d1.plus(new Monom("2x")); //plus(plus(2x,4x),2x)
c1.mul(d1);//mult(mult(plus(plus(plus(2x,4x),mult(x,x)),4x),2),plus(plus(2x,4x),2x))
c1.div(new Monom("2x")); //div(mult(mult(plus(plus(plus(2x,4x),mult(x,x)),4x),2),plus(plus(2x,4x),2x)),2x)
double x1 =c1.f(2);
System.out.println(x1);
```
The result is 192
Equals function: the complex function's equals is return true if for all x, |c1.f(x) - c2.f(x)|<=EPSILON. This is not solvable problem, because if i need to compare polynom and complex function, there is no solution to check for all x, so my solution is partial solution, i use for loop in the interval [-10000,10000], i+=0.30 and check if|c1.f(x) - c2.f(x)|<=EPSILON.

Function GUI
***
Implements from collection, contains a collection of linkedList<function>, this class capable to draw functions, save the collection to txt file, read functions from txt file, read Gui parameters from JSON file and write to JSON file.
For drawing this class use XChart lib, you can read about this liberty here: https://knowm.org/open-source/xchart/
The class contains two constructors:

```java
public Functions_GUI(int width,int hight,int resolution,Range x,Range y)
```
width is the width of the window, hight is the height of the window, resolution of the functions, [x0,x1] , [y0,y1]
Or you can use default constructor:
```java
public Functions_GUI() 
```
 Defined: width = 1000, height = 600, resolution = 400, [-10,10],[-15,15]
The function initFromFile- read from text file (the path is a parameter) the functions, add to the collection by using function.initFromString().
The function saveToFile- create text file (the path is a parameter), and store the functions. 
The function drawFunctions(gui params...), actually drawing the function by using XChart. first of all create an array that contains the x values in the range [x0,x1] (the jump between Xi to Xi+1 dependence on the resolution), then for each function I created an array that store the y value (f(x)) , and then i added these arrays to XYChart, and displayed to the screen the function. For example this functions in the range [-5,15],[15,15] 
```java
plus(-1.0x^4+2.4x^2+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0)
plus(div(1.0x^1+1.0x^0,mult(mult(1.0x^1+3.0x^0,1.0x^1-2.0x^0),1.0x^1-4.0x^0)),2.0x^0)
div(plus(-1.0x^4+2.4x^2+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0),-1.0x^4+2.4x^2+3.1x^0)
-1.0x^4+2.4x^2+3.1x^0
```
<a href="https://ibb.co/kK6wNDt"><img src="https://i.ibb.co/7vNZspq/Untitled.png" alt="Untitled" border="0"></a> 

The function drawFunctions(String path) draw the collection by the the JSON file, the form of the JSON is:
```json
{
	"Width":1000,
	"Height":600,
	"Resolution":400,
	"Range_X":[-15,15],
	"Range_Y":[-5,15]
}
```
To read from json and writing a json file, i use gson of google.
