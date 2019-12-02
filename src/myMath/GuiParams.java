package myMath;

public class GuiParams 
{
	private double width,hight,resolution;
	private Range range_x;
	private Range range_y;
	
	public GuiParams(double width,double hight,double resolution,Range x,Range y)
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
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHight() {
		return hight;
	}
	public void setHight(double hight) {
		this.hight = hight;
	}
	public double getResolution() {
		return resolution;
	}
	public void setResolution(double resolution) {
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
}
