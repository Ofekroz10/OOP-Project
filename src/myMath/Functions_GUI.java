package myMath;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.SeriesMarkers;
 
 



public class Functions_GUI implements functions
{
	public LinkedList<function> collection;
	private final String fileName = "object.txt";
	private final String jSonName ="object.json";
	private GuiParams guiParams;

	
	public Functions_GUI()
	{
		collection = new LinkedList<function>();
		guiParams = new GuiParams(1000,600,200,new Range(-10,10),new Range(-15,15));
		
	}
	public Functions_GUI(double width,double hight,double resolution,Range x,Range y)
	{
		collection = new LinkedList<function>();
		guiParams = new GuiParams(width,hight,resolution,x,y);
	}
	
	@Override
	public boolean add(function arg0) {
		return(collection.add(arg0));
		
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		return (collection.addAll(arg0));
		
	}

	@Override
	public void clear() {
		collection.clear();
		
	}

	@Override
	public boolean contains(Object arg0) {
		return (collection.contains(arg0));
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return (collection.containsAll(arg0));
	}

	@Override
	public boolean isEmpty() {
		return (collection.isEmpty());
	}

	@Override
	public Iterator<function> iterator() {
		return collection.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		return (collection.remove(arg0));
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return collection.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return collection.retainAll(arg0);
	}

	@Override
	public int size() {
		return collection.size();
	}

	@Override
	public Object[] toArray() {
		return collection.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return(collection.toArray(arg0));
	}

	@Override
	public void initFromFile(String file) throws IOException {
		try {
			BufferedReader abc = new BufferedReader(new FileReader(file));
			LinkedList<String> lines = new LinkedList<String>();
			String line;
			
			while((line = abc.readLine()) != null) {
				ComplexFunction c = new ComplexFunction("plus",null,null);
			    lines.add(line);
			   function a  = c.initFromString(line);
			   System.out.println(a.toString());
			    
			}
			abc.close();
			fromJson(jSonName);
		}
		catch (FileNotFoundException e) {
			throw new FileNotFoundException("file is not exist");
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void saveToFile(String file) throws IOException {
		try (FileWriter writer = new FileWriter(file);
				 BufferedWriter bw = new BufferedWriter(writer)) {

				bw.write(this.collectionAsStr());
				bw.close();
				this.toJson(jSonName);
				
		}
				
		catch(FileNotFoundException  e)
		{
			throw new  FileNotFoundException ("the file is not writble");
		}
		

		
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		
		 XYChart chart = new  XYChartBuilder().width(800).height(600).theme(ChartTheme.Matlab).build();
		 
		 // Customize Chart
		 int j=0;
		 int k =0;
		 int arrayLen = getLen(0.05);
		 double[][]values = new double[collection.size()][arrayLen];
		 double[] xAsis = new double[arrayLen];
		 boolean init = false;
		 //chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
		for (function funcs : collection) 
		{
			 for(double i =guiParams.getRange_x().get_min();i<guiParams.getRange_x().get_max();i+=0.05)
			 {
				 values[j][k] = funcs.f(i);
				 System.out.println("("+i+","+funcs.f(i)+")");
				 if(!init)
					 xAsis[k] = i;
				 k++;
			 }
			 
			 k=0;
			 init  = true;
			 chart.addSeries(funcs.toString(), xAsis, values[j]).setMarker(SeriesMarkers.NONE);   
			 j++;
		}
			chart.getStyler().setLegendPosition(LegendPosition.InsideSW);
			chart.getStyler().setXAxisMax(guiParams.getRange_x().get_max());
		    chart.getStyler().setXAxisMin(guiParams.getRange_x().get_min());
		    chart.getStyler().setYAxisMax(guiParams.getRange_y().get_max());
		    chart.getStyler().setYAxisMin(guiParams.getRange_y().get_min());
		
		
		 new SwingWrapper(chart).displayChart();
		
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		
	}
	
	private String collectionAsStr()
	{
		StringBuilder sb = new StringBuilder();
		for (function temp : collection) {
			sb.append( temp.toString()+"\n");
		}
		return sb.toString();
	}
	private void toJson(String filename) throws JsonIOException, IOException
	{
		try (FileWriter writer = new FileWriter(filename)) {
		    Gson gson = new GsonBuilder().create();
		    gson.toJson(this.guiParams, writer);
		}
				
		catch(FileNotFoundException  e)
		{
			throw new  FileNotFoundException ("the file is not writble");
		}
		catch(Exception e)
		{
			throw e;
		}
		
	}
	private void fromJson(String filename) throws JsonIOException, IOException
	{
		try {
			Gson gson = new Gson();
			JsonReader reader = new JsonReader(new FileReader(filename));
			GuiParams reviews = gson.fromJson(reader, GuiParams.class);
			System.out.println(reviews.toString());
		}
				
		catch(FileNotFoundException  e)
		{
			throw new  FileNotFoundException ("the file is not writble");
		}
		catch(Exception e)
		{
			throw e;
		}
		
	}
	private int getLen(double jump)
	{
		int count = 0;
		for (double i =guiParams.getRange_x().get_min();i<guiParams.getRange_x().get_max();i+=jump)
		{
			count++;
		}
		return count;
	}
	
	
}
