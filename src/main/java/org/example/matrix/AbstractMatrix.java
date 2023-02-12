package org.example.matrix;

import org.example.matrix.exception.InternalIncompatibleDimensionsException;
import org.example.matrix.exception.NegativeDimensionException;
import org.example.matrix.exception.PairwiseIncompatibleDimensionsException;

public abstract class AbstractMatrix {
	/*
	 * _nRow Number of rows
	 */
	protected final int _nRow;
	/*
	 * _nCol Number of columns
	 */
	protected final int _nCol;

	protected AbstractMatrix(int nRow, int nCol)
			throws NegativeDimensionException {
		if (nRow < 0 || nCol < 0) {
			throw new NegativeDimensionException(this);
		}
		this._nRow = nRow;
		this._nCol = nCol;
	}

	/**
	 * @param lhs nRow x nCol matrix
	 * @param rhs mRow x mCol matrix
	 * @throws PairwiseIncompatibleDimensionsException if nRow != mRow
	 *                                                 || nCol != mCol
	 */
	protected static void checkPairwiseCompatibility(AbstractMatrix lhs, AbstractMatrix rhs)
			throws PairwiseIncompatibleDimensionsException {
		if (lhs._nCol != rhs.getNCol() || lhs._nRow != rhs.getNRow()) {
			throw new PairwiseIncompatibleDimensionsException(lhs, rhs);
		}
	}

	/**
	 * @param lhs nRow x nCol matrix
	 * @param rhs mRow x mCol matrix
	 * @throws InternalIncompatibleDimensionsException if nCol != mRow
	 */
	protected static void checkInternalCompatibility(AbstractMatrix lhs, AbstractMatrix rhs)
			throws InternalIncompatibleDimensionsException {
		if (lhs._nCol != rhs.getNRow()) {
			throw new InternalIncompatibleDimensionsException(lhs, rhs);
		}
	}

	/**
	 * @return new int equals `this._nRow`
	 */
	public int getNRow() {
		return this._nRow;
	}

	/**
	 * @return new int equals `this._nCol`
	 */
	public int getNCol() {
		return this._nCol;
	}

}
