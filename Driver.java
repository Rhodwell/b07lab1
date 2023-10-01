/*
 * Rhodwell Jireh Malicdem
 * October 1, 2023
 * CSCB07: Driver - Lab 2
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Driver 
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		double[] coefficient = {2.1, -1, 4.3};
		int[] exponent = {0, 5, 3};
		File testFile = new File("C:\\Eclipse\\CSCB07\\Equation.txt");
		//Testing Constructors
		Polynomial noArgument = new Polynomial();
		Polynomial twoArguments = new Polynomial(coefficient, exponent);
		Polynomial fileArgument = new Polynomial(testFile);
		//Testing Polynomial.add()
		System.out.println("Addition");
		System.out.println("--------");
		System.out.println("Two Argument + No Arguments: " + twoArguments.add(noArgument));
		System.out.println("File Argument + No Argument: " + fileArgument.add(noArgument));
		System.out.println("Two Arguments + File Argument: " + twoArguments.add(fileArgument));
		System.out.println("");
		//Testing Polynomial.multply()
		System.out.println("Multiplication");
		System.out.println("--------------");
		System.out.println("Two Argument * No Arguments: " + twoArguments.multiply(noArgument));
		System.out.println("File Argument * No Argument: " + fileArgument.multiply(noArgument));
		System.out.println("Two Argument * File Arguments: " + twoArguments.multiply(fileArgument));
		System.out.println();
		//Testing Polynomial.evaluate()
		System.out.println("Evaluate");
		System.out.println("--------");
		System.out.println("Two Arguments @ x=0: " + twoArguments.evaluate(0));
		System.out.println("File Arguments @ x=5: " + fileArgument.evaluate(5));
		System.out.println();
		//Testing Polynomial.hasRoot()
		System.out.println("hasRoot");
		System.out.println("--------");
		System.out.println("Two Arguments @ x=0: " + twoArguments.hasRoot(0));
		System.out.println("File Arguments @ x=5: " + fileArgument.hasRoot(5));
		//Testing Polynomial.saveToFile()
		try 
		{
			twoArguments.multiply(fileArgument).saveToFile("C:\\Eclipse\\CSCB07\\newerEquation.txt");
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
