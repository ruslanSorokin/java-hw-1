package org.example.matrix.exception;

import org.example.matrix.AbstractMatrix;

public class NegativeDimensionException extends RuntimeException {

	public NegativeDimensionException(AbstractMatrix matrix) {
		super("expected non-negative dimensions:"
				+ String.format("%dx%d",
						matrix.getNRow(),
						matrix.getNCol()));
	}
}
