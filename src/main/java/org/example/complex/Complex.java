package org.example.complex;

public class Complex {
	private double _real;
	private double _imag;

	public Complex() {
		this._real = this._imag = 0;
	}

	public Complex(double real, double imag) {
		this._real = real;
		this._imag = imag;
	}

	public Complex(double real) {
		this._real = real;
		this._imag = 0;
	}

	public Complex(Complex other) {
		this(other._real, other._imag);
	}

	public double getReal() {
		return this._real;
	}

	public double getImag() {
		return this._imag;
	}

	public Complex add(Complex other) {
		Complex._add(this, other);
		return this;
	}

	protected static void _add(Complex lhs, Complex rhs) {
		lhs._real += rhs._real;
		lhs._imag += rhs._imag;
	}

	public static Complex add(Complex lhs, Complex rhs) {
		var ret = new Complex(lhs);
		Complex._add(ret, rhs);
		return ret;
	}

	public Complex sub(Complex other) {
		Complex._sub(this, other);
		return this;
	}

	protected static void _sub(Complex lhs, Complex rhs) {
		lhs._real -= rhs._real;
		lhs._imag -= rhs._imag;
	}

	public static Complex sub(Complex lhs, Complex rhs) {
		var ret = new Complex(lhs);
		Complex._sub(ret, rhs);
		return ret;
	}

	public Complex mul(Complex other) {
		Complex._mul(this, other);
		return this;
	}

	protected static void _mul(Complex lhs, Complex rhs) {
		var real = lhs._real * rhs._real - lhs._imag * rhs._imag;
		var imag = lhs._real * rhs._imag + lhs._imag * rhs._real;

		lhs._real = real;
		lhs._imag = imag;
	}

	public static Complex mul(Complex lhs, Complex rhs) {
		var ret = new Complex(lhs);
		Complex._mul(ret, rhs);
		return ret;
	}

	public Complex div(Complex other) {
		Complex._div(this, other);
		return this;
	}

	protected static void _div(Complex lhs, Complex rhs) {
		var sqrtSum = rhs._real * rhs._real + rhs._imag * rhs._imag;
		var real = (lhs._real * rhs._real + lhs._imag * rhs._imag) / sqrtSum;
		var imag = (lhs._imag * rhs._real + lhs._real * rhs._imag) / sqrtSum;

		lhs._real = real;
		lhs._imag = imag;
	}

	public static Complex div(Complex lhs, Complex rhs) {
		var ret = new Complex(lhs);
		Complex._div(ret, rhs);
		return ret;
	}

	@Override
	public String toString() {
		return String.format("%.2f", this._real) + " + (" + String.format("%.2f", this._imag) + "i)";
	}
}
