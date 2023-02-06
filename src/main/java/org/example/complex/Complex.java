package org.example.complex;

public class Complex {
    private final double _real;
    private final double _imag;

    public Complex(){
        this._real = this._imag = 0;
    }

    public Complex(double real, double imag){
        this._real = real;
        this._imag = imag;
    }

    public Complex(double real){
        this._real = real;
        this._imag = 0;
    }

    public Complex(Complex other){
        this(other._real, other._imag);
    }

    public double getReal(){
        return this._real;
    }

    public double getImage(){
        return this._imag;
    }

    public Complex sum(Complex other){
        return new Complex(this._real + other._real, this._imag + other._imag);
    }

    public Complex sub(Complex other){
        return new Complex(this._real - other._real, this._imag - other._imag);
    }

    public Complex mul(Complex other){
        var real = this._real * other._real - this._imag * other._imag;
        var imag = this._real * other._imag + this._imag * other._real;
        return new Complex(real, imag);
    }

    public Complex div(Complex other){
        var sqrtSum = other._real * other._real + other._imag * other._imag;
        var real = (this._real * other._real + this._imag * other._imag) / sqrtSum;
        var imag = (this._imag * other._real + this._real * other._imag) / sqrtSum;

        return new Complex(real, imag);
    }
}
