package org.example.matrix.exception;

public class NegativeDimensionException extends RuntimeException {

	public NegativeDimensionException(int nRow, int nCol) {
		super("expected non-negative demensions:"
				+ String.format("%dx%d", nRow, nCol));
	}
}