package org.example.complex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComplexTest {
	@Test
	void testConstructors() {
		Assertions.assertEquals(new Complex(), new Complex(0, 0));
		Assertions.assertEquals(new Complex(), new Complex(0));
		Assertions.assertEquals(new Complex(), new Complex(0d, 0d));
		Assertions.assertEquals(new Complex(100), new Complex(100, 0));
	}

	@Test
	void testAddition() {
		var lhs = new Complex(100, 100);
		var rhs = new Complex(200, 200);
		var lhs_backup = new Complex(lhs);
		var rhs_backup = new Complex(rhs);
		var res = new Complex(300, 300);

		Assertions.assertEquals(lhs.add(rhs), res); // lhs + rhs == res
		Assertions.assertEquals(rhs.add(lhs), res); // rhs + lhs == res
		Assertions.assertEquals(lhs, lhs_backup); // lhs == lhs_backup
		Assertions.assertEquals(rhs, rhs_backup); // rhs == rhs_backup

		Assertions.assertEquals(lhs.addeq(rhs), res); // lhs += rhs == res
		Assertions.assertEquals(rhs.addeq(lhs_backup), res); // rhs += lhs_backup(lhs) == res

		Assertions.assertEquals(lhs, res); // lhs == res
		Assertions.assertNotEquals(lhs, lhs_backup); // lhs != lhs_backup
		Assertions.assertEquals(rhs, res); // rhs == res
		Assertions.assertNotEquals(rhs, rhs_backup); // rhs != rhs_backup
	}

	@Test
	void testSubtraction() {
		var lhs = new Complex(300, 300);
		var rhs = new Complex(200, 200);
		var lhs_backup = new Complex(lhs);
		var rhs_backup = new Complex(rhs);
		var res = new Complex(100, 100);

		Assertions.assertEquals(lhs.sub(rhs), res); // lhs - rhs == res
		Assertions.assertEquals(lhs, lhs_backup); // lhs == lhs_backup
		Assertions.assertEquals(rhs, rhs_backup); // rhs == rhs_backup

		Assertions.assertEquals(lhs.subeq(rhs), res); // lhs -= rhs == res
		Assertions.assertEquals(lhs, res); // lhs == res
		Assertions.assertNotEquals(lhs, lhs_backup); // lhs != lhs_backup
	}

	@Test
	void testMultiplication() {
		var lhs = new Complex(5, 0);
		var rhs = new Complex(10, 0);
		var lhs_backup = new Complex(lhs);
		var rhs_backup = new Complex(rhs);
		var res = new Complex(50, 0);

		Assertions.assertEquals(lhs.mul(rhs), res); // lhs * rhs == res
		Assertions.assertEquals(lhs, lhs_backup); // lhs == lhs_backup
		Assertions.assertEquals(rhs, rhs_backup); // rhs == rhs_backup

		Assertions.assertEquals(lhs.muleq(rhs), res); // lhs *= rhs == res
		Assertions.assertEquals(lhs, res); // lhs == res
		Assertions.assertNotEquals(lhs, lhs_backup); // lhs != lhs_backup
	}

	@Test
	void testDivision() {
		var lhs = new Complex(0, 100);
		var rhs = new Complex(1, 1);
		var lhs_backup = new Complex(lhs);
		var rhs_backup = new Complex(rhs);
		var res = new Complex(50, 50);

		Assertions.assertEquals(lhs.div(rhs), res); // lhs / rhs == res
		Assertions.assertEquals(lhs, lhs_backup); // lhs == lhs_backup
		Assertions.assertEquals(rhs, rhs_backup); // rhs == rhs_backup

		Assertions.assertEquals(lhs.diveq(rhs), res); // lhs /= rhs == res
		Assertions.assertEquals(lhs, res); // lhs == res
		Assertions.assertEquals(lhs.muleq(rhs), lhs); // lhs /= rhs *= rhs == lhs
		Assertions.assertEquals(lhs, lhs_backup); // lhs != lhs_backup
	}
}
