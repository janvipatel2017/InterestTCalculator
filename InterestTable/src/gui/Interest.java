package gui;

public class Interest {

	public double computeSimpleInterest(double principal, double rate, double years) {
		double simpleInterest = principal + (principal * (rate/100) * years);
		return simpleInterest;
	}
	
	public double computeCompoundInterest(double principal, double rate, double years) {
		double compoundInterest = Math.pow((1 + rate/100), years);
		compoundInterest *= principal;
		return compoundInterest;
	}
}
