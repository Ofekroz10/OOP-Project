package Ex1;
/**
 * This class represents a simple 1D range of shape [min,max]
 * @author boaz_benmoshe
 *
 */
public class Range {
	private double _min, _max;
	public Range(double min, double max) {
		set_min(min);
		set_max(max);
	}
	/*
	 * chek if is inside the range
	 */
	public boolean isIn(double d) {
		boolean inSide = false;
		if(d>=this.get_min() && d<=this.get_max()) {inSide=true;}
		return inSide;
	}
	/*
	 * print the range
	 */
	public String toString() {
		String ans = "["+this.get_min()+","+this.get_max()+"]";
		if(this.isEmpty()) {ans = "Empty Range";}
		return ans;
	}
	/*
	 * chek if is the range is empty
	 */
	public boolean isEmpty() {
		return this.get_min()>this.get_max();
	}
	/*
	 * return max range
	 */
	public double get_max() {
		return _max;
	}
	/*
	 * return min of the range
	 */
	private void set_max(double _max) {
		this._max = _max;
	}
	/*
	 * getter and setter to min
	 */
	public double get_min() {
		return _min;
	}
	private void set_min(double _min) {
		this._min = _min;
	}
	public Range copy() {
		return new Range(_min,_max);
	}
	/*
	 * chek if two ranges are equals
	 */
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		else if(o instanceof Range)
		{
			Range r = (Range)o;
			return(_min==r.get_min()&&_max==r.get_max());
		}
		return false;
	}
}
