package org.example.matrix;

import org.example.matrix.exception.InternalIncompatibleDimensionsException;
import org.example.matrix.exception.NegativeDimensionException;
import org.example.matrix.exception.NonSquareMatrix;
import org.example.matrix.exception.PairwiseIncompatibleDimensionsException;

public class IntegerMatrix extends AbstractGenericMatrix<Long> {

	/*
	 * Constructs a new IntegerMatrix with size `nRow` x `nCol`
	 * throws NegativeDimensionException if any of dimension is negative
	 */
	public IntegerMatrix(int nRow, int nCol)
			throws NegativeDimensionException {
		this(nRow, nCol, 0l);
	}

	/*
	 * Constructs a new IntegerMatrix with size `nRow` x `nCol` and fills everething
	 * with value of `val`
	 * throws NegativeDimensionException if any of dimension is negative
	 */
	public IntegerMatrix(int nRow, int nCol, Long val)
			throws NegativeDimensionException {
		super(nRow, nCol);
		this.data = new Long[this._nRow][this._nCol];
		this.fill(val);
	}

	/*
	 * Constructs a new IntegerMatrix with size `nRow` x `nCol` and fills everething
	 * with value of `val`
	 * throws NegativeDimensionException if any of dimension is negative
	 */
	public IntegerMatrix(int nRow, int nCol, Integer val)
			throws NegativeDimensionException {
		super(nRow, nCol);
		this.data = new Long[this._nRow][this._nCol];
		this.fill(Long.valueOf(val));
	}

	/*
	 * Constructs a new IntegerMatrix with size `nRow` x `nCol`
	 */
	public IntegerMatrix(IntegerMatrix other) {
		this(other._nRow, other._nCol);
		this.copy(other, this);
	}

	/**
	 * Assignment addition operation equals `this += other`
	 *
	 * @param other matrix which will be added to `this` matrix
	 * @return this matrix
	 * @throws PairwiseIncompatibleDimensionsException if matrices are not pairwise
	 *                                                 compatible
	 */
	@Override
	public AbstractGenericMatrix<Long> addeq(AbstractGenericMatrix<Long> other)
			throws PairwiseIncompatibleDimensionsException {
		AbstractMatrix.checkPairwiseCompatibility(this, other);
		return this._add(this, other);
	}

	/**
	 * Addition operation equals `this + other`
	 *
	 * @param other matrix which will be added to `this` matrix
	 * @return new matrix
	 * @throws PairwiseIncompatibleDimensionsException if matrices are not pairwise
	 *                                                 compatible
	 */
	@Override
	public AbstractGenericMatrix<Long> add(AbstractGenericMatrix<Long> other)
			throws PairwiseIncompatibleDimensionsException {
		AbstractMatrix.checkPairwiseCompatibility(this, other);
		var res = new IntegerMatrix(this);
		return this._add(res, other);
	}

	private AbstractGenericMatrix<Long> _add(
			AbstractGenericMatrix<Long> lhs,
			AbstractGenericMatrix<Long> rhs) {
		for (int i = 0; i < lhs._nRow; ++i) {
			for (int j = 0; j < lhs._nCol; ++j) {
				lhs.data[i][j] = lhs.data[i][j] + rhs.data[i][j];
			}
		}
		return lhs;
	}

	/**
	 * Assignment subtraction operation equals `this -= other`
	 *
	 * @param other matrix which will be subtracted from this matrix
	 * @return this matrix
	 * @throws PairwiseIncompatibleDimensionsException if matrices are not pairwise
	 *                                                 compatible
	 */
	@Override
	public AbstractGenericMatrix<Long> subeq(AbstractGenericMatrix<Long> other)
			throws PairwiseIncompatibleDimensionsException {
		AbstractMatrix.checkPairwiseCompatibility(this, other);
		return this._sub(this, other);
	}

	/**
	 * Subtraction operation equals `this - other`
	 *
	 * @param other matrix which will be subtracted from this matrix
	 * @return new matrix
	 * @throws PairwiseIncompatibleDimensionsException if matrices are not pairwise
	 *                                                 compatible
	 */
	@Override
	public AbstractGenericMatrix<Long> sub(AbstractGenericMatrix<Long> other)
			throws PairwiseIncompatibleDimensionsException {
		AbstractMatrix.checkPairwiseCompatibility(this, other);
		var res = new IntegerMatrix(this);
		return this._sub(res, other);
	}

	private AbstractGenericMatrix<Long> _sub(
			AbstractGenericMatrix<Long> lhs,
			AbstractGenericMatrix<Long> rhs) {
		for (int i = 0; i < lhs._nRow; ++i) {
			for (int j = 0; j < lhs._nCol; ++j) {
				lhs.data[i][j] = lhs.data[i][j] - rhs.data[i][j];
			}
		}
		return lhs;

	}

	/**
	 * Multiplication operation equals `this * other`
	 *
	 * @param other matrix by which `this` matrix will be multiplied
	 * @return new matrix
	 * @throws InternalIncompatibleDimensionsException if matrices are not internal
	 *                                                 compatible
	 */
	@Override
	public AbstractGenericMatrix<Long> mul(
			AbstractGenericMatrix<Long> lhs,
			AbstractGenericMatrix<Long> rhs)
			throws InternalIncompatibleDimensionsException {
		AbstractMatrix.checkInternalCompatibility(lhs, rhs);
		var res = new IntegerMatrix(lhs._nRow, rhs._nCol);
		for (int i = 0; i < lhs._nRow; i++) {
			for (int j = 0; j < rhs._nCol; j++) {
				for (int k = 0; k < rhs._nRow; k++) {
					res.data[i][j] += lhs.data[i][k] * rhs.data[k][j];
				}
			}
		}
		return res;
	}

	/**
	 * Assignment transpose operation
	 *
	 * @return this matrix
	 * @throws NonSquareMatrix if matrix is not square-like
	 */
	@Override
	public AbstractGenericMatrix<Long> transposeeq()
			throws NonSquareMatrix {
		AbstractMatrix.checkSquareness(this);
		return this._transpose(this);
	}

	/**
	 * Transpose operation
	 *
	 * @return new matrix
	 * @throws NonSquareMatrix if matrix is not square-like
	 */
	@Override
	public AbstractGenericMatrix<Long> transpose()
			throws NonSquareMatrix {
		AbstractMatrix.checkSquareness(this);
		return this._transpose(new IntegerMatrix(this));
	}

	/*
	 * Returns new matrix in a triangle form
	 */
	protected IntegerMatrix triangleForm() {
		var ret = new IntegerMatrix(this);

		for (int i = 0; i < this._nRow; ++i) {
			for (int j = i + 1; j < this._nRow; ++j) {

				var tmp = ret.data[j][i] / ret.data[i][i];
				for (int k = 0; k < this._nRow; ++k) {
					ret.data[j][k] -= ret.data[i][k] * tmp;
				}
			}
		}

		return ret;
	}

	/**
	 * Determinant
	 *
	 * @return new Long
	 * @throws NonSquareMatrix if matrix is not square-like
	 */
	@Override
	public Long getDeterminant() throws NonSquareMatrix {
		AbstractMatrix.checkSquareness(this);
		var triangleForm = this.triangleForm();

		var ret = 1l;
		for (int i = 0; i < this._nRow; ++i) {
			ret *= triangleForm.data[i][i];
		}
		return ret;
	}

}
