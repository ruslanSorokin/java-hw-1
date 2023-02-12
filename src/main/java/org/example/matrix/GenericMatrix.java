package org.example.matrix;

import org.example.matrix.exception.PairwiseIncompatibleDimensionsException;
import org.example.matrix.exception.IncompatibleInnerDimensionsExceptions;
import org.example.matrix.exception.NegativeDimensionException;

public abstract class GenericMatrix<T> extends AbstractMatrix {
	protected T[][] _data = null;

	public GenericMatrix(int nRow, int nCol)
			throws NegativeDimensionException {
		super(nRow, nCol);
	}

	protected void copy(GenericMatrix<T> src, GenericMatrix<T> dst) {
		for (int i = 0; i < dst._nRow; ++i) {
			for (int j = 0; j < dst._nCol; ++j) {
				dst._data[i][j] = src._data[i][j];
			}
		}
	}

	public abstract T get(int i, int j);

	public abstract void set(T val, int i, int j);

	public abstract GenericMatrix<T> add(GenericMatrix<T> other)
			throws PairwiseIncompatibleDimensionsException;

	public abstract GenericMatrix<T> sub(GenericMatrix<T> other)
			throws PairwiseIncompatibleDimensionsException;

	public abstract GenericMatrix<T> mul(GenericMatrix<T> other)
			throws IncompatibleInnerDimensionsExceptions;
}
