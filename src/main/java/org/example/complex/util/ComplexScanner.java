package org.example.complex.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.regex.Pattern;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import java.util.Scanner;

import org.example.complex.Complex;

/*
 * Wrapper for the standard Scanner class
 */
public class ComplexScanner {

	/*
	 * \(?([+-]?(\d*\.?\d+))?([+-]?(\d*\.?\d+))i\)?
	 */
	private String _regex = "\\(?([+-]?(\\d*\\.?\\d+))?([+-]?(\\d*\\.?\\d+))i\\)?";
	private Pattern _pattern = Pattern.compile(_regex, Pattern.DOTALL);
	private Scanner _scanner;

	public ComplexScanner(InputStream source) {
		_scanner = new Scanner(source);
	}

	public ComplexScanner(File source) throws FileNotFoundException {
		_scanner = new Scanner(source);
	}

	public ComplexScanner(String source) {
		_scanner = new Scanner(source);
	}

	public boolean hasNextComplex() {
		return _scanner.hasNext(_pattern);
	}

	public Complex nextComplex() {
		if (!_scanner.hasNext()) {
			throw new NoSuchElementException();
		}
		if (this.hasNextComplex()) {
			var matcher = _pattern.matcher(_scanner.next());
			if (matcher.find()) {
				var real = Double.parseDouble(matcher.group(1));
				var imag = Double.parseDouble(matcher.group(3));
				return new Complex(real, imag);
			}
		}
		throw new InputMismatchException();
	}

	public void close() {
		_scanner.close();
	}

}
