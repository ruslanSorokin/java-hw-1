package org.example;

import java.util.Scanner;

import org.example.complex.util.ComplexScanner;
import org.example.matrix.ComplexMatrix;
import org.example.matrix.exception.NegativeDimensionException;

public class Main {
	public static void main(String[] args) {
		var s = new Scanner(System.in);
		var cS = new ComplexScanner(System.in);

		System.out.println("Enter N:");
		var n = s.nextInt();
		System.out.println("Enter M:");
		var m = s.nextInt();

		try {
			var matrix = new ComplexMatrix(n, m);
			matrixInput(cS, matrix);

			System.out.println(matrix);
		} catch (NegativeDimensionException e) {
			e.printStackTrace();
		}

		s.close();
		cS.close();
	}

	static void matrixInput(ComplexScanner cS, ComplexMatrix matrix) {
		for (int i = 0; i < matrix.getNRow(); ++i) {
			for (int j = 0; j < matrix.getNCol(); ++j) {
				if (!cS.hasNextComplex()) {
					return;
				}
				matrix.data[i][j] = cS.nextComplex();
			}
		}
	}

}
