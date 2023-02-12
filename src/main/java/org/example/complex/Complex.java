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

	public double getImage() {
		return this._imag;
	}

	public Complex add(Complex other) {
		Complex.add(this, other);
		return this;
	}

	public Complex sub(Complex other) {
		Complex.sub(this, other);
		return this;
	}

	public Complex mul(Complex other) {
		Complex.mul(this, other);
		return this;
	}

	public Complex div(Complex other) {
		Complex.div(this, other);
		return this;
	}

	protected static void add(Complex lhs, Complex rhs) {
		lhs._real += rhs._real;
		lhs._imag += rhs._imag;
	}

	protected static void sub(Complex lhs, Complex rhs) {
		lhs._real -= rhs._real;
		lhs._imag -= rhs._imag;
	}

	protected static void mul(Complex lhs, Complex rhs) {
		var real = lhs._real * rhs._real - lhs._imag * rhs._imag;
		var imag = lhs._real * rhs._imag + lhs._imag * rhs._real;

		lhs._real = real;
		lhs._imag = imag;
	}

	protected static void div(Complex lhs, Complex rhs) {
		var sqrtSum = rhs._real * rhs._real + rhs._imag * rhs._imag;
		var real = (lhs._real * rhs._real + lhs._imag * rhs._imag) / sqrtSum;
		var imag = (lhs._imag * rhs._real + lhs._real * rhs._imag) / sqrtSum;

		lhs._real = real;
		lhs._imag = imag;
	}
}
