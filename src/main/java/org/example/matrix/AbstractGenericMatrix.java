package org.example.matrix;

import org.example.matrix.exception.PairwiseIncompatibleDimensionsException;
import org.example.matrix.exception.InternalIncompatibleDimensionsException;
import org.example.matrix.exception.NegativeDimensionException;

public abstract class AbstractGenericMatrix<T>
		extends AbstractMatrix {
	/*
	 * Stores cells
	 */
	public T[][] data = null;

	protected AbstractGenericMatrix(int nRow, int nCol)
			throws NegativeDimensionException {
		super(nRow, nCol);
	}

	/**
	 * @param src matrix from which elements will be copied
	 * @param dst matrix into which elements will be copied
	 */
	protected void copy(AbstractGenericMatrix<T> src, AbstractGenericMatrix<T> dst) {
		for (int i = 0; i < dst._nRow; ++i) {
			for (int j = 0; j < dst._nCol; ++j) {
				dst.data[i][j] = src.data[i][j];
			}
		}
	}

	/**
	 * @param val value which will be set
	 */
	protected void fill(T val) {
		for (int i = 0; i < this._nRow; ++i) {
			for (int j = 0; j < this._nCol; ++j) {
				this.data[i][j] = val;
			}
		}
	}

	/**
	 * @param i row index
	 * @param j column index
	 * @return boolean true if both indexes are in bounds
	 *         otherwise return false
	 */
	private boolean isInBounds(int i, int j) {
		if (i < 0 || i >= this._nRow || j < 0 || j >= this._nCol) {
			return false;
		}
		return true;
	}

	/**
	 * @param i row index
	 * @param j column index
	 * @return T
	 * @throws ArrayIndexOutOfBoundsException if one of the indexes is out of bound
	 */
	public T get(int i, int j)
			throws ArrayIndexOutOfBoundsException {
		return this.data[i][j];
	}

	/**
	 * @param val value which will be set
	 * @param i   row index
	 * @param j   column index
	 * @throws ArrayIndexOutOfBoundsException if one of the indexes is out of bound
	 */
	public void set(T val, int i, int j)
			throws ArrayIndexOutOfBoundsException {
		this.data[i][j] = val;
	}

	/**
	 * @param i row index
	 * @param j column index
	 * @return T
	 */
	public T getAt(int i, int j) {
		if (!isInBounds(i, j)) {
			return null;
		}

		return this.data[i][j];
	}

	/**
	 * @param val value which will be set
	 * @param i   row index
	 * @param j   column index
	 */
	public void setAt(T val, int i, int j) {
		if (!isInBounds(i, j)) {
			return;
		}

		this.data[i][j] = val;
	}

	/**
	 * @return this matrix
	 * @throws PairwiseIncompatibleDimensionsException if matrices are not pairwise
	 *                                                 compatible
	 */
	public abstract AbstractGenericMatrix<T> addeq(AbstractGenericMatrix<T> other)
			throws PairwiseIncompatibleDimensionsException;

	/**
	 * @return new matrix
	 * @throws PairwiseIncompatibleDimensionsException if matrices are not pairwise
	 *                                                 compatible
	 */
	public abstract AbstractGenericMatrix<T> add(AbstractGenericMatrix<T> other)
			throws PairwiseIncompatibleDimensionsException;

	/**
	 * @return this matrix
	 * @throws PairwiseIncompatibleDimensionsException if matrices are not pairwise
	 *                                                 compatible
	 */
	public abstract AbstractGenericMatrix<T> subeq(AbstractGenericMatrix<T> other)
			throws PairwiseIncompatibleDimensionsException;

	/**
	 * @return new matrix
	 * @throws PairwiseIncompatibleDimensionsException if matrices are not pairwise
	 *                                                 compatible
	 */
	public abstract AbstractGenericMatrix<T> sub(AbstractGenericMatrix<T> other)
			throws PairwiseIncompatibleDimensionsException;

	/**
	 * @return new matrix
	 * @throws InternalIncompatibleDimensionsException if matrices are not
	 *                                                 internal
	 *                                                 compatible
	 */
	public abstract AbstractGenericMatrix<T> mul(
			AbstractGenericMatrix<T> lhs,
			AbstractGenericMatrix<T> rhs)
			throws InternalIncompatibleDimensionsException;

	/**
	 * @return String
	 */
	@Override
	public String toString() {
		var ret = new String();

		for (int i = 0; i < this._nRow; ++i) {
			for (int j = 0; j < this._nCol; ++j) {
				ret += this.data[i][j] + "   ";
			}
			ret += '\n';
		}
		return ret;
	}
}
