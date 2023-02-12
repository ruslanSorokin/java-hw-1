package org.example.matrix;

import org.example.matrix.exception.IncompatibleInnerDimensionsExceptions;
import org.example.matrix.exception.NegativeDimensionException;
import org.example.matrix.exception.PairwiseIncompatibleDimensionsException;

public abstract class AbstractMatrix {
	protected final int _nRow;
	protected final int _nCol;

	public AbstractMatrix(int nRow, int nCol)
			throws NegativeDimensionException {
		if (nRow < 0 || nCol < 0) {
			throw new NegativeDimensionException(this);
		}
		this._nRow = nRow;
		this._nCol = nCol;
	}

	protected static void checkPairwiseCompatibility(AbstractMatrix lhs, AbstractMatrix rhs)
			throws PairwiseIncompatibleDimensionsException {
		if (lhs._nCol != rhs.getNCol() || lhs._nRow != rhs.getNRow()) {
			throw new PairwiseIncompatibleDimensionsException(lhs, rhs);
		}
	}

	protected static void checkInternallyCompatibility(AbstractMatrix lhs, AbstractMatrix rhs)
			throws IncompatibleInnerDimensionsExceptions {
		if (lhs._nCol != rhs.getNRow()) {
			throw new IncompatibleInnerDimensionsExceptions(lhs, rhs);
		}
	}

	public int getNRow() {
		return this._nRow;
	}

	public int getNCol() {
		return this._nCol;
	}

}
