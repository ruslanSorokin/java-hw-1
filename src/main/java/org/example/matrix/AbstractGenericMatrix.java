package org.example.matrix;

import org.example.matrix.exception.PairwiseIncompatibleDimensionsException;
import org.example.matrix.exception.InternalIncompatibleDimensionsException;
import org.example.matrix.exception.NegativeDimensionException;

public abstract class AbstractGenericMatrix<T>
		extends AbstractMatrix {
	public T[][] data = null;

	protected AbstractGenericMatrix(int nRow, int nCol)
			throws NegativeDimensionException {
		super(nRow, nCol);
	}

	protected void copy(AbstractGenericMatrix<T> src, AbstractGenericMatrix<T> dst) {
		for (int i = 0; i < dst._nRow; ++i) {
			for (int j = 0; j < dst._nCol; ++j) {
				dst.data[i][j] = src.data[i][j];
			}
		}
	}

	protected void fill(T val) {
		for (int i = 0; i < this._nRow; ++i) {
			for (int j = 0; j < this._nCol; ++j) {
				this.data[i][j] = val;
			}
		}
	}

	private boolean isInBounds(int i, int j) {
		if (i < 0 || i > this._nRow || j < 0 || j > this._nCol) {
			return true;
		}
		return false;
	}

	public T get(int i, int j)
			throws ArrayIndexOutOfBoundsException {
		return this.data[i][j];
	}

	public void set(T val, int i, int j)
			throws ArrayIndexOutOfBoundsException {
		this.data[i][j] = val;
	}

	public T getAt(int i, int j) {
		if (!isInBounds(i, j)) {
			return null;
		}

		return this.data[i][j];
	}

	public void setAt(T val, int i, int j) {
		if (!isInBounds(i, j)) {
			return;
		}

		this.data[i][j] = val;
	}

	public abstract AbstractGenericMatrix<T> add(AbstractGenericMatrix<T> other)
			throws PairwiseIncompatibleDimensionsException;

	public abstract AbstractGenericMatrix<T> sub(AbstractGenericMatrix<T> other)
			throws PairwiseIncompatibleDimensionsException;

	public abstract AbstractGenericMatrix<T> mul(AbstractGenericMatrix<T> other)
			throws InternalIncompatibleDimensionsException;
}
