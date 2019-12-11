package Ex1;

public class GuiParams 
{
	private int width,hight,resolution;
	private Range range_x;
	private Range range_y;
	/*
	 *  Initialize this GuiParams with given parameters.
	 */
	public GuiParams(int width,int hight,int resolution,Range x,Range y)
	{
		this.width = width;
		this.hight = hight;
		this.resolution = resolution;
		this.range_x= x;
		this.range_y = y;
	}
	/*
	 * return witdth and height and res and rangeX and rangeY
	 */
	@Override
	public String toString()
	{
		return ("Width: " +width+", "+"hight: "+ hight+", resolution: "+resolution+", range_x"+ range_x.toString()+", range_y"+range_y.toString());
	}
	/*
	 * return width
	 */
	public int getWidth() {
		return width;
	}
	/*
	 * Checker with valid values entered
	 */
	public boolean FileItegrity() {
		return this.width> 0 && this.hight >0 && this.resolution > 0 && validateRange(this.range_x)&& validateRange(this.range_y);
	}
	/*
	 * Checker with valid values entered
	 */
	private static boolean validateRange(Range range) {
		boolean flag = false;


		flag = (range.get_max()-range.get_min())>=0;


		return range != null && flag;
	}
	/*
	 * getters and setters
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHight() {
		return hight;
	}
	public void setHight(int hight) {
		this.hight = hight;
	}
	public int getResolution() {
		return resolution;
	}
	public void setResolution(int resolution) {
		this.resolution = resolution;
	}
	public Range getRange_x() {
		return range_x;
	}
	public void setRange_x(Range range_x) {
		this.range_x = range_x;
	}
	public Range getRange_y() {
		return range_y;
	}
	public void setRange_y(Range range_y) {
		this.range_y = range_y;
	}
	/*
	 * 
	 *  return new GuiParams, that contains the sames values of parameters.
	 */
	public GuiParams copy() {
		return new GuiParams(this.width,this.hight,this.resolution,this.getRange_x().copy(),this.getRange_y().copy());
	}
	/*
	 * return true if the values of the parameters are the same to o parameters.
	 * GuiParamsArray
	 */
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		else if (o instanceof GuiParams)
		{
			GuiParams gui = (GuiParams)o;
			return (gui.getWidth()==width&&gui.getHight()==hight&&gui.getRange_x().equals(range_x)&&gui.getRange_y().equals(range_y));
		}
		return false;

	}

}
