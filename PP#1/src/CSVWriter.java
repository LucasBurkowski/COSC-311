import java.io.*;

public class CSVWriter {
	PrintWriter writer;
	StringBuilder output = new StringBuilder();
	
	public CSVWriter(String file){
		try {
			writer = new PrintWriter(new File(file+".csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addToFile(int  x, int first, int second, int third, int fourth, int fifth){
		output.append(x);
		output.append(',');
		output.append(first);
		output.append(',');
		output.append(second);
		output.append(',');
		output.append(third);
		output.append(',');
		output.append(fourth);
		output.append(',');
		output.append(fifth);
		output.append('\n');
	}
	
	public void initFile(){
		output.append("x");
		output.append(',');
		output.append("Customers/tick");
		output.append(',');
		output.append("CustomerServed vs. ticks");
		output.append(',');
		output.append("CustomersWait vs. ticks");
		output.append(',');
		output.append("Benefit per tick");
		output.append(',');
		output.append("Cost per tick");
		output.append('\n');
	}
	
	public void writeFile(){
		writer.write(output.toString());
		writer.close();
	}
}
