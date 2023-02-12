package org.example.matrix;

import org.example.matrix.exception.NegativeDimensionException;

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

	public AbstractMatrix(AbstractMatrix other) {
		this(other._nRow, other._nCol);
	}

	public int getNRow() {
		return this._nRow;
	}

	public int getNCol() {
		return this._nCol;
	}

}
