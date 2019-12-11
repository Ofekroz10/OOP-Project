package Ex1;

import java.io.Serializable;

public class GuiParamsArray implements Serializable
{
	private int Width,Height,Resolution;
	private double[] Range_X;
	private double[] Range_Y;
	
	public GuiParamsArray()
	{

	}
	/*
	 * return GuiParams object the exist data of the object.
	 */
	public GuiParams toGuiParams()
	{
		GuiParams params = new GuiParams(Width,Height,Resolution,new Range(Range_X[0],Range_X[1]),new Range(Range_Y[0],Range_Y[1]));
		return params;
	}

}
