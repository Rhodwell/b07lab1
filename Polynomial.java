/*
 * Rhodwell Jireh Malicdem
 * September 13, 2023
 * CSCB07: Lab 1
*/
public class Polynomial
{
	private double[] coefficient;

	public Polynomial()
	{
		coefficient = new double[1];
		coefficient[0] = 0.0;
	}// end Polynomial()

	public Polynomial(double[] coefficient)
	{
		this.coefficient = coefficient.clone();
	}// end Polynomial()

	public Polynomial add(Polynomial poly)
	{
		double[] sum = poly.coefficient.clone();
		for (int i = 0; i < coefficient.length; i++)
		{
			sum[i] += coefficient[i];
		} // end loop
		Polynomial newPoly = new Polynomial(sum);
		return newPoly;
	}// end add()

	public double evaluate(double xValue)
	{
		double result = 0;
		for (int i = 0; i < coefficient.length; i++)
		{
			result += coefficient[i] * Math.pow(xValue, i);
		} // end loop
		return result;
	}// end evaluate()

	public boolean hasRoot(double xRoot)
	{
		return evaluate(xRoot) == 0;
	}// end hasRoot()
}