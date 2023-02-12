package org.example.matrix.exception;

import org.example.matrix.AbstractMatrix;

public class InternalIncompatibleDimensionsException extends RuntimeException {

	public InternalIncompatibleDimensionsException(AbstractMatrix lhs, AbstractMatrix rhs) {
		super("expected equality of the inner dimensions:"
				+ String.format("%dx%d",
						lhs.getNRow(), lhs.getNCol())
				+ " "
				+ String.format("%dx%d",
						rhs.getNRow(), rhs.getNCol()));
	}
}
