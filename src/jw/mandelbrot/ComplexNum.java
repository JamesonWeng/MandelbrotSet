package jw.mandelbrot;

public class ComplexNum {
	private double r, i;
	
	public ComplexNum (double re, double im) {
		this.r = re;
		this.i = im;
	}
	
	public static double re (ComplexNum a) {
		return a.r;
	}
	
	public static double im (ComplexNum a) {
		return a.i;
	}
	
	public void display () {
		System.out.printf (this.r + " %c " + this.i + "i\n", (this.i > 0)? '+':'-');
	}
	
	public static ComplexNum plus (ComplexNum a, ComplexNum b) {
		return new ComplexNum (a.r + b.r, a.i + b.i);
	}
	
	public static ComplexNum minus (ComplexNum a, ComplexNum b) {
		return new ComplexNum (a.r - b.r, a.i - b.i);
	}
	
	public static ComplexNum times (ComplexNum a, ComplexNum b) {
		return new ComplexNum (a.r * b.r - a.i * b.i, a.r * b.i + a.i * b.r);
	}
	
	public static ComplexNum divide (ComplexNum a, ComplexNum b) {
		double mag = b.r * b.r + b.i * b.i;
		return new ComplexNum ((a.r * b.r + a.i * b.i) / mag, (a.i * b.r - a.r * b.i) / mag);
	}
	
	public static double mag (ComplexNum a) {
		return Math.sqrt(a.r*a.r + a.i*a.i);
	}


}
