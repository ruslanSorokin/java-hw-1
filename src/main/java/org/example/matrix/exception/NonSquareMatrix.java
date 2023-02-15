package org.example.matrix.exception;

import org.example.matrix.AbstractMatrix;

public class NonSquareMatrix extends RuntimeException {

	public NonSquareMatrix(AbstractMatrix matrix) {
		super("expected Square matrix:"
				+ String.format("%dx%d",
						matrix.getNRow(),
						matrix.getNCol()));
	}
}
