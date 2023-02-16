package org.example.matrix;

import org.example.complex.Complex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComplexMatrixTest {
	@Test
	void testAddition() {
		var mat1 = new ComplexMatrix(10, 10); // 10x10 with 0+0i
		var mat1_backup = new ComplexMatrix(mat1);
		var mat2 = new ComplexMatrix(10, 10, new Complex(10, 0)); // 10x10 with 10+0i
		var mat2_backup = new ComplexMatrix(mat2);

		var res = new ComplexMatrix(10, 10, new Complex(10, 0));

		Assertions.assertEquals(res, mat1.add(mat2));
		Assertions.assertEquals(mat1, mat1_backup);
		Assertions.assertEquals(mat2, mat2_backup);

		Assertions.assertEquals(res, mat1.addeq(mat2));
		Assertions.assertNotEquals(mat1, mat1_backup);
		Assertions.assertEquals(mat2, mat2_backup);

	}

	@Test
	void testSubtraction() {
		var mat1 = new ComplexMatrix(10, 10, new Complex(5)); // 10x10 with 5+0i
		var mat1_backup = new ComplexMatrix(mat1);
		var mat2 = new ComplexMatrix(10, 10, new Complex(10, 0)); // 10x10 with 10+0i
		var mat2_backup = new ComplexMatrix(mat2);

		var res = new ComplexMatrix(10, 10, new Complex(5, 0));

		Assertions.assertEquals(res, mat2.sub(mat1));
		Assertions.assertEquals(mat1, mat1_backup);
		Assertions.assertEquals(mat2, mat2_backup);

		Assertions.assertEquals(res, mat2.subeq(mat1));
		Assertions.assertNotEquals(mat2, mat2_backup);
		Assertions.assertEquals(mat1, mat1_backup);

	}

	@Test
	void testMultiplication() {
		var mat1 = new ComplexMatrix(10, 10, new Complex(5)); // 10x10 with 5+0i
		var mat1_backup = new ComplexMatrix(mat1);
		var mat2 = new ComplexMatrix(10, 10, new Complex(10)); // 10x10 with 10+0i
		var mat2_backup = new ComplexMatrix(mat2);

		var res = new ComplexMatrix(10, 10, new Complex(500, 0));

		Assertions.assertEquals(res, mat1.mul(mat2));
		Assertions.assertEquals(mat1, mat1_backup);
		Assertions.assertEquals(mat2, mat2_backup);

	}
}
