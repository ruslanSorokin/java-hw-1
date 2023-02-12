package org.example.matrix.exception;

import org.example.matrix.AbstractMatrix;

public class PairwiseIncompatibleDimensionsException extends RuntimeException {

	public PairwiseIncompatibleDimensionsException(AbstractMatrix lhs, AbstractMatrix rhs) {
		super("expected pairwise equality of these dimensions:"
				+ String.format("%dx%d",
						lhs.getNRow(), lhs.getNCol())
				+ " "
				+ String.format("%dx%d",
						rhs.getNRow(), rhs.getNCol()));
	}
}