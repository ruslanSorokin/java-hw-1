package org.example.complex.util;

import org.example.complex.Complex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScannerTest {
	@Test
	void testDouble() {
		var str = "1.0+2.0i";
		var s = new ComplexScanner(str);
		Assertions.assertEquals(s.nextComplex(), new Complex(1.0, 2.0));

		str = ".0+0.5i";
		s = new ComplexScanner(str);
		Assertions.assertEquals(s.nextComplex(), new Complex(0, 0.5));

		str = "1.0+0.0i";
		s = new ComplexScanner(str);
		Assertions.assertEquals(s.nextComplex(), new Complex(1, 0));
	}

	@Test
	void testDoubleSign() {
		var str = "-1.0+2.0i";
		var s = new ComplexScanner(str);
		Assertions.assertEquals(s.nextComplex(), new Complex(-1.0, 2.0));

		str = ".0-0.5i";
		s = new ComplexScanner(str);
		Assertions.assertEquals(s.nextComplex(), new Complex(0, -0.5));

		str = "-1.0+1.0i";
		s = new ComplexScanner(str);
		Assertions.assertEquals(s.nextComplex(), new Complex(-1, 1));
	}

	@Test
	void testInt() {
		var str = "1234+25550i";
		var s = new ComplexScanner(str);
		Assertions.assertEquals(s.nextComplex(), new Complex(1234, 25550));

		str = "100+55i";
		s = new ComplexScanner(str);
		Assertions.assertEquals(s.nextComplex(), new Complex(100, 55));

		str = "1+0i";
		s = new ComplexScanner(str);
		Assertions.assertEquals(s.nextComplex(), new Complex(1, 0));
	}

	@Test
	void testIntSign() {
		var str = "-1234-25550i";
		var s = new ComplexScanner(str);
		Assertions.assertEquals(s.nextComplex(), new Complex(-1234, -25550));

		str = "-100+55i";
		s = new ComplexScanner(str);
		Assertions.assertEquals(s.nextComplex(), new Complex(-100, 55));

		str = "1-0i";
		s = new ComplexScanner(str);
		Assertions.assertEquals(s.nextComplex(), new Complex(1, -0));
	}

	@Test
	void testIntDouble() {
		var str = "-1234-25.550i";
		var s = new ComplexScanner(str);
		Assertions.assertEquals(s.nextComplex(), new Complex(-1234, -25.550));

		str = "-0.100+55i";
		s = new ComplexScanner(str);
		Assertions.assertEquals(s.nextComplex(), new Complex(-0.100, 55));

		str = "1-0.5i";
		s = new ComplexScanner(str);
		Assertions.assertEquals(s.nextComplex(), new Complex(1, -0.5));
	}
}
