import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Lab2 {

	static ArrayList<String[]> line_list = new ArrayList<String[]>(); //an arraylist created to store eachline

	static HashMap<String, Integer> var;

	Lab2(){
		var = new HashMap<String, Integer>();
	}
	
	static int num1=0;
	static int num2 = 0;
	static int num3 = 0;

	public static void read_file(String filename){ 
		
		try (BufferedReader br = new BufferedReader(new FileReader(filename)))
		{
			String CurrentLine;
	
			//this will read file line by line and line is then stored in currentline
			while ((CurrentLine = br.readLine()) != null){

				String[] tokens = CurrentLine.split(" "); //line is tokenized whenever it sees a space in it
				line_list.add(tokens);	
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public static void checkline(){
		int list_length = line_list.size();

		for(int k=0; k<list_length; k++){
			String[] x = line_list.get(k);
			
			if(x[0].matches("Let") || x[0].matches("let")){
				declaration(x);
				
			}

			else if(x[0].matches("Print") || x[0].matches("print")){
				display(x);
			}

			else{
				operation(x);
			}
		}	
	}

	public static void declaration(String[] x){
		
		System.out.println("Variable Declaration:");
		var.put(x[1], Integer.parseInt(x[3]));
		System.out.println(x[1] + "=" + Integer.parseInt(x[3]));
		System.out.println(" ");
	}

	public static void operation(String[] x){
		
		System.out.println("Arithmetic operation:");
		if(var.get(x[0]) == null){
			System.out.println("Exception: No such variable.");
		}
		else{
			check_arguments(x);
			
			if(x[3].contains("+")){
				num1=num2+num3;
				var.put(x[0],num1);
				System.out.println(x[0]+ "=" +var.get(x[0]));
			}
			else if(x[3].contains("-")){
				num1=num2-num3;
				var.put(x[0],num1);
				System.out.println(x[0]+ "=" +var.get(x[0]));
			}
			else if(x[3].contains("/")){
				num1=num2*num3;
				var.put(x[0],num1);
				System.out.println(x[0]+ "=" +var.get(x[0]));
			}
			else{
				num1=num2/num3;
				var.put(x[0],num1);
				System.out.println(x[0]+ "=" +var.get(x[0]));
			}
		}
		System.out.println(" ");
	}
	
	public static void check_arguments(String[] x){
		
		if(var.get(x[2]) == null){
			num2=Integer.parseInt(x[2]);
			num3=Integer.parseInt(x[4]);
		}
		else{
			num2=var.get(x[2]);
			num3=var.get(x[4]);
		}
		
	}

	public static void display(String[] x){

		System.out.println("Print Variable:");
		
		if(var.get(x[1]) == null){
			System.out.println("Exception: No such variable.");
		}
		
		else{
			System.out.println(x[1] + "=" + var.get(x[1]));
		}
		System.out.println(" ");
	}
}
