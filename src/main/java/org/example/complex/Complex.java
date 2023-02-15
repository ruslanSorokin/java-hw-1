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
	 * @return new double - real part of the Complex Number
	 */
	public double getReal() {
		return this._real;
	}

	/**
	 * @return new double - imaginary part of the Complex number
	 */
	public double getImag() {
		return this._imag;
	}

	/**
	 * Assignment addition operation equals: `this += other`
	 *
	 * @param other Complex
	 * @return this Complex
	 */
	public Complex addeq(Complex other) {
		return Complex._add(this, other);
	}

	/**
	 * Addition operation equals: `this + other`
	 *
	 * @param other Complex
	 * @return new Complex
	 */
	public Complex add(Complex other) {
		return Complex._add(new Complex(this), other);
	}

	/*
	 * Modifies `res` and returns it back
	 */
	private static Complex _add(Complex res, Complex other) {
		res._real += other._real;
		res._imag += other._imag;

		return res;
	}

	/**
	 *
	 * Assignment subtraction operation equals: `this -= other`
	 *
	 * @param other Complex
	 * @return this Complex
	 */
	public Complex subeq(Complex other) {
		return Complex._sub(this, other);
	}

	/**
	 * Subtraction operation equals: `this - other`
	 *
	 * @param other Complex
	 * @return new Complex
	 */
	public Complex sub(Complex other) {
		return Complex._sub(new Complex(this), other);
	}

	/*
	 * Modifies `res` and returns it back
	 */
	private static Complex _sub(Complex res, Complex other) {
		res._real -= other._real;
		res._imag -= other._imag;

		return res;
	}

	/**
	 * Assignment multiplication operation equals: `this *= other`
	 *
	 * @param other Complex
	 * @return this Complex
	 */
	public Complex muleq(Complex other) {
		return Complex._mul(this, other);
	}

	/**
	 * Multiplication operation equals: `this * other`
	 *
	 * @param other Complex
	 * @return new Complex
	 */
	public Complex mul(Complex other) {
		return Complex._mul(new Complex(this), other);
	}

	/*
	 * Modifies `res` and returns it back
	 */
	private static Complex _mul(Complex res, Complex other) {
		var real = res._real * other._real - res._imag * other._imag;
		var imag = res._real * other._imag + res._imag * other._real;

		res._real = real;
		res._imag = imag;

		return res;
	}

	/**
	 * Assignment division operation equals: `this /= other`
	 *
	 * @param other Complex
	 * @return this Complex
	 */
	public Complex diveq(Complex other) {
		return Complex._div(this, other);
	}

	/**
	 * Division operation equals `this / other`
	 *
	 * @param other Complex
	 * @return new Complex
	 */
	public Complex div(Complex other) {
		return Complex._div(new Complex(this), other);
	}

	/*
	 * Modifies `res` and returns it back
	 */
	private static Complex _div(Complex res, Complex other) {
		var sqrtSum = other._real * other._real + other._imag * other._imag;
		var real = (res._real * other._real + res._imag * other._imag) / sqrtSum;
		var imag = (res._imag * other._real + res._real * other._imag) / sqrtSum;

		res._real = real;
		res._imag = imag;

		return res;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj.getClass() != this.getClass()) {
			return false;
		}

		var other = (Complex) obj;
		return (this._imag == other._imag && this._real == other._real);
	}

	@Override
	public String toString() {
		return String.format("(%.2f + %.2fi)", this._real, this._imag);
	}

	/**
	 * Phase
	 *
	 * @return new double
	 */
	public double getPhase() {
		return Math.atan2(this._imag, this._real);
	}

	/**
	 * Trigonometric form
	 *
	 * @return new String
	 */
	public String getTrigonometric() {
		var rad = Math.sqrt(Math.sqrt(this._real) + Math.sqrt(this._imag));
		var pi = Math.toDegrees(this.getPhase());
		return String.format("%.2f cos(%.2f) + i * sin(%.2f)", rad, pi, pi);
	}
}
