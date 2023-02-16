package org.example.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegerMatrixTest {
	@Test
	void testAddition() {
		var mat1 = new IntegerMatrix(10, 10); // 10x10 with 0
		var mat1_backup = new IntegerMatrix(mat1);
		var mat2 = new IntegerMatrix(10, 10, 10); // 10x10 with 10
		var mat2_backup = new IntegerMatrix(mat2);

		var res = new IntegerMatrix(10, 10, 10);

		Assertions.assertEquals(res, mat1.add(mat2));
		Assertions.assertEquals(mat1, mat1_backup);
		Assertions.assertEquals(mat2, mat2_backup);

		Assertions.assertEquals(res, mat1.addeq(mat2));
		Assertions.assertNotEquals(mat1, mat1_backup);
		Assertions.assertEquals(mat2, mat2_backup);

	}

	@Test
	void testSubtraction() {
		var mat1 = new IntegerMatrix(10, 10, 5); // 10x10 with 5
		var mat1_backup = new IntegerMatrix(mat1);
		var mat2 = new IntegerMatrix(10, 10, 10); // 10x10 with 10
		var mat2_backup = new IntegerMatrix(mat2);

		var res = new IntegerMatrix(10, 10, 5);

		Assertions.assertEquals(res, mat2.sub(mat1));
		Assertions.assertEquals(mat1, mat1_backup);
		Assertions.assertEquals(mat2, mat2_backup);

		Assertions.assertEquals(res, mat2.subeq(mat1));
		Assertions.assertNotEquals(mat2, mat2_backup);
		Assertions.assertEquals(mat1, mat1_backup);

	}

	@Test
	void testMultiplication() {
		var mat1 = new IntegerMatrix(10, 10, 5); // 10x10 with 5
		var mat1_backup = new IntegerMatrix(mat1);
		var mat2 = new IntegerMatrix(10, 10, 10); // 10x10 with 10
		var mat2_backup = new IntegerMatrix(mat2);

		var res = new IntegerMatrix(10, 10, 500);

		Assertions.assertEquals(res, mat1.mul(mat2));
		Assertions.assertEquals(mat1, mat1_backup);
		Assertions.assertEquals(mat2, mat2_backup);

	}
}
