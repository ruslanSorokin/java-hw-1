package org.example.complex;

public class Complex {
	private double _real;
	private double _imag;

	/*
	 * Constructs a new Complex Number with parameters 0, 0
	 * Equals `Complex(0, 0)`
	 */
	public Complex() {
		this._real = this._imag = 0;
	}

	/*
	 * Constructs a new Complex Number with parameters `real`, `imag`
	 */
	public Complex(double real, double imag) {
		this._real = real;
		this._imag = imag;
	}

	/*
	 * Constructs a new Complex Number with parameters `real`, 0
	 * Equals `Complex(real, 0)`
	 */
	public Complex(double real) {
		this._real = real;
		this._imag = 0;
	}

	/*
	 * Constructs a new Complex Number with parameters `other._real`, `other._imag`
	 */
	public Complex(Complex other) {
		this(other._real, other._imag);
	}

	/**
	 * @return new double real part of the Complex Number
	 */
	public double getReal() {
		return this._real;
	}

	/**
	 * @return new double imaginary part of the Complex number
	 */
	public double getImag() {
		return this._imag;
	}

	/**
	 * Assignment addition operation equals `this += other`
	 *
	 * @param other Complex
	 * @return `this` + `other`
	 */
	public Complex add(Complex other) {
		Complex._add(this, other);
		return this;
	}

	private static void _add(Complex lhs, Complex rhs) {
		lhs._real += rhs._real;
		lhs._imag += rhs._imag;
	}

	/**
	 * Addition operation equals `lhs` + `rhs`
	 *
	 * @param lhs Complex
	 * @param rhs Complex
	 * @return new Complex `ret` = `lhs` + `rhs`
	 */
	public static Complex add(Complex lhs, Complex rhs) {
		var ret = new Complex(lhs);
		Complex._add(ret, rhs);
		return ret;
	}

	/**
	 *
	 * Assignment subtraction operation equals `this -= other`
	 *
	 * @param other Complex
	 * @return this - other
	 */
	public Complex sub(Complex other) {
		Complex._sub(this, other);
		return this;
	}

	private static void _sub(Complex lhs, Complex rhs) {
		lhs._real -= rhs._real;
		lhs._imag -= rhs._imag;
	}

	/**
	 *
	 * Subtraction operation equals `lhs` - `rhs`
	 *
	 * @param lhs Complex
	 * @param rhs Complex
	 * @return new Complex `ret` = `lhs` - `rhs`
	 */
	public static Complex sub(Complex lhs, Complex rhs) {
		var ret = new Complex(lhs);
		Complex._sub(ret, rhs);
		return ret;
	}

	/**
	 * Assignment multiplication operation equals `this *= other`
	 *
	 * @param other Complex
	 * @return `this` * `other`
	 */
	public Complex mul(Complex other) {
		Complex._mul(this, other);
		return this;
	}

	private static void _mul(Complex lhs, Complex rhs) {
		var real = lhs._real * rhs._real - lhs._imag * rhs._imag;
		var imag = lhs._real * rhs._imag + lhs._imag * rhs._real;

		lhs._real = real;
		lhs._imag = imag;
	}

	/**
	 * Multiplication operation equals `lhs` * `rhs`
	 *
	 * @param lhs Complex
	 * @param rhs Complex
	 * @return new Complex `ret` = `lhs` * `rhs`
	 */
	public static Complex mul(Complex lhs, Complex rhs) {
		var ret = new Complex(lhs);
		Complex._mul(ret, rhs);
		return ret;
	}

	/**
	 * Assignment division operation equals `this /= other`
	 *
	 * @param other Complex
	 * @return `this` / `other`
	 */
	public Complex div(Complex other) {
		Complex._div(this, other);
		return this;
	}

	private static void _div(Complex lhs, Complex rhs) {
		var sqrtSum = rhs._real * rhs._real + rhs._imag * rhs._imag;
		var real = (lhs._real * rhs._real + lhs._imag * rhs._imag) / sqrtSum;
		var imag = (lhs._imag * rhs._real + lhs._real * rhs._imag) / sqrtSum;

		lhs._real = real;
		lhs._imag = imag;
	}

	/**
	 * Division operation equals `lhs` / `rhs`
	 *
	 * @param lhs Complex
	 * @param rhs Complex
	 * @return new Complex `ret` = `lhs` / `rhs`
	 */
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
