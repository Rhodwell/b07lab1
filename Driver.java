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
		System.out.println("No Argument + Two Arguments: " + noArgument.add(twoArguments));
		System.out.println("No Argument + File Argument: " + noArgument.add(fileArgument));
		System.out.println("Two Arguments + File Argument: " + twoArguments.add(fileArgument));
		System.out.println("");
		//Testing Polynomial.multply()
		System.out.println("Multiplication");
		System.out.println("--------------");
		System.out.println("No Argument * Two Arguments: " + noArgument.multiply(twoArguments));
		System.out.println("No Argument * File Arguments: " + noArgument.multiply(fileArgument));
		System.out.println("Two Argument * File Arguments: " + twoArguments.multiply(fileArgument));
		System.out.println();
		//Testing Polynomial.evaluate()
		System.out.println("Evaluate");
		System.out.println("--------");
		System.out.println("No Argument @ x=5: " + noArgument.evaluate(5));
		System.out.println("Two Arguments @ x=5: " + twoArguments.evaluate(5));
		System.out.println("File Arguments @ x=5: " + fileArgument.evaluate(5));
		System.out.println();
		//Testing Polynomial.hasRoot()
		System.out.println("hasRoot");
		System.out.println("--------");
		System.out.println("No Argument @ x=5: " + noArgument.hasRoot(5));
		System.out.println("Two Arguments @ x=5: " + twoArguments.hasRoot(5));
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
