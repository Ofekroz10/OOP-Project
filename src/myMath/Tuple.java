package myMath;

import java.io.Serializable;

public class Tuple implements Serializable
{
	private String op;
	private function f;
	
	public Tuple(String o,function f)
	{
		op = o;
		this.f =f;
	}
	public function getF() {return f;}
	public String getOp() {return op;}
}
