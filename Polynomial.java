/*
 * Rhodwell Jireh Malicdem
 * September 13, 2023
 * CSCB07: Lab 1 & Lab 2
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Polynomial
{
	double[] coefficient;
	int[] exponent;
	public Polynomial()
	{
		coefficient = null;
		exponent = null;
	}// end Polynomial()
	public Polynomial(double[] coefficient, int[] exponent)
	{
		this.coefficient = coefficient.clone();
		this.exponent = exponent.clone();
	}// end Polynomial()
	public Polynomial(File file) throws FileNotFoundException
	{
		int counter = 0;
		Scanner fileRead = new Scanner(file);
		String equation = "";
		while(fileRead.hasNextLine())
		{
			equation += fileRead.nextLine();
		}//end loop
		equation = equation.replace("-", "+-");
		String[] splitConstant = equation.split("\\+");
		for(String i: splitConstant)
		{
			if(i != "") counter++;
		}//end loop
		coefficient = new double[counter];
		exponent = new int[counter];
		counter = 0;
		for(int i=0; i<splitConstant.length; i++)
		{
			if(splitConstant[i].contains("x"))
			{
				coefficient[counter] = Double.parseDouble(splitConstant[i].split("x")[0]);
				exponent[counter] = Integer.parseInt(splitConstant[i].split("x")[1]);
				counter++;
			}
			else if(splitConstant[i] != "")
			{
				coefficient[counter] = Double.parseDouble(splitConstant[i]);
				exponent[counter] = 0;
				counter++;
			}//end if
		}//end loop
		fileRead.close();
	}//end Polynomial()
	public int getMax(int[] list)
	{
		int max = 0;
		for(int i=0; i<list.length; i++)
		{
			if(list[i] > max) max = list[i];
		}//end loop
		return max;
	}//end getMax
	public int getIndex(int[] list, int value)
	{
		for(int i=0; i<list.length; i++)
		{
			if(list[i] == value) return i;
		}//end loop
		return -1;
	}//getIndex
	public Polynomial add(Polynomial newPoly)
	{
		if(newPoly.coefficient == null) return new Polynomial(coefficient, exponent);
		int length = Math.max(getMax(exponent), getMax(newPoly.exponent)) + 1, counter = 0;
		int[] tempExponent = new int[length];
		double[] tempCoefficient = new double[length];
		for(int i=0; i<length; i++)
		{
			tempExponent[i] = i;
			tempCoefficient[i] = ((getIndex(exponent, i) != -1)?coefficient[getIndex(exponent, i)]:0.0) + ((getIndex(newPoly.exponent, i) != -1)?newPoly.coefficient[getIndex(newPoly.exponent, i)]:0.0);
		}//end loop
		for(double i: tempCoefficient)
		{
			if(i != 0.0) counter++;
		}//end loop
		int[] newExponent = new int[counter];
		double[] newCoefficient = new double[counter];
		counter = 0;
		for(int i=0; i<length; i++)
		{
			if(tempCoefficient[i] != 0.0)
			{
				newCoefficient[counter] = tempCoefficient[i];
				newExponent[counter] = tempExponent[i];
				counter++;
			}//end if
		}//end loop
		return new Polynomial(newCoefficient, newExponent);
	}// end add()
	public Polynomial multiply(Polynomial newPoly)
	{
		if(newPoly.coefficient == null) return new Polynomial();
		int counter = 0;
		int[] indexCopy = new int[newPoly.exponent.length * exponent.length];
		double[] tempCoefficient = new double[newPoly.coefficient.length * coefficient.length];
		int[] tempExponent = new int[newPoly.exponent.length * exponent.length];
		for(int i=0; i<newPoly.coefficient.length; i++)
		{
			for(int j=0; j<coefficient.length; j++)
			{
				tempCoefficient[counter] = newPoly.coefficient[i] * coefficient[j];
				tempExponent[counter] = newPoly.exponent[i] + exponent[j];
				counter++;
			}//end loop
		}//end loop
		counter = 0;
		for(int i=0; i<tempExponent.length-1; i++)
		{
			for(int j=i+1; j<tempExponent.length; j++)
			{
				if(tempExponent[i] == tempExponent[j] && getIndex(indexCopy, j) == -1)
				{
					tempCoefficient[i] += tempCoefficient[j];
					tempCoefficient[j] = 0;
					indexCopy[counter] = j;
					counter++;
				}//end if
			}//end loop
		}//end loop
		return new Polynomial(tempCoefficient, tempExponent).add(new Polynomial(new double[0], new int[0]));
	}//end multiply()
	public double evaluate(double xValue)
	{
		double result = 0;
		for (int i = 0; i < coefficient.length; i++)
		{
			result += coefficient[i] * Math.pow(xValue, exponent[i]);
		} // end loop
		return Math.round(result * 10) / 10.0;
	}// end evaluate()
	public boolean hasRoot(double xRoot)
	{
		return evaluate(xRoot) == 0;
	}// end hasRoot()
	public void saveToFile(String fileName) throws IOException
	{
		String equation = "";
		if(!new File(fileName).createNewFile()) new FileWriter(fileName, false).close();
		try (FileWriter file = new FileWriter(fileName))
		{
			if(coefficient != null)
			{
				for(int i=0; i<coefficient.length; i++)
				{
					equation += ((exponent[i] == 0)? Math.round(coefficient[i]*10)/10.0: Math.round(coefficient[i]*10)/10.0 + "x" + exponent[i]) + ((i==exponent.length-1 || coefficient[i+1]<0)?"":"+");  
				}//end loop
			}
			else
			{
				equation = "0";
			}
			file.write(equation);
		}//end try
	}//end saveToFile()
	public String toString()
	{
		if(coefficient == null) return "0";
		String equation = "";
		for(int i=0; i<exponent.length; i++)
		{
			equation += ((exponent[i] == 0)? Math.round(coefficient[i]*10)/10.0: Math.round(coefficient[i]*10)/10.0 + "x" + exponent[i]) + ((i==exponent.length-1 || coefficient[i+1]<0)?"":"+");
		}//end loop
		return equation;
	}//end toString()
}