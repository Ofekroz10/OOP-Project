package myMath;

public class GuiParams 
{
	private int width,hight,resolution;
	private Range range_x;
	private Range range_y;
	
	public GuiParams(int width,int hight,int resolution,Range x,Range y)
	{
		this.width = width;
		this.hight = hight;
		this.resolution = resolution;
		this.range_x= x;
		this.range_y = y;
	}
	@Override
	public String toString()
	{
		return ("Width: " +width+", "+"hight: "+ hight+", resolution: "+resolution+", range_x"+ range_x.toString()+", range_y"+range_y.toString());
	}
	public int getWidth() {
		return width;
	}
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
	public GuiParams copy() {
		return new GuiParams(this.width,this.hight,this.resolution,this.getRange_x().copy(),this.getRange_y().copy());
	}
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
