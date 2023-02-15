package org.example.matrix;

import org.example.matrix.exception.PairwiseIncompatibleDimensionsException;
import org.example.matrix.exception.InternalIncompatibleDimensionsException;
import org.example.matrix.exception.NegativeDimensionException;
import org.example.matrix.exception.NonSquareMatrix;
import org.example.complex.Complex;

public class ComplexMatrix
		extends AbstractGenericMatrix<Complex> {

	/*
	 * Constructs a new ComplexMatrix with size `nRow` x `nCol`
	 * throws NegativeDimensionException if any of dimension is negative
	 */
	public ComplexMatrix(int nRow, int nCol)
			throws NegativeDimensionException {
		super(nRow, nCol);
		this.data = new Complex[this._nRow][this._nCol];
		this.fill(new Complex(0, 0));
	}

	/*
	 * Constructs a new ComplexMatrix with size `nRow` x `nCol` and fills everething
	 * with value of `val`
	 * throws NegativeDimensionException if any of dimension is negative
	 */
	public ComplexMatrix(int nRow, int nCol, Complex val)
			throws NegativeDimensionException {
		this(nRow, nCol);
		this.fill(val);
	}

	/*
	 * Constructs a new ComplexMatrix with size `nRow` x `nCol`
	 */
	public ComplexMatrix(ComplexMatrix other) {
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
	public AbstractGenericMatrix<Complex> addeq(AbstractGenericMatrix<Complex> other)
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
	public AbstractGenericMatrix<Complex> add(AbstractGenericMatrix<Complex> other)
			throws PairwiseIncompatibleDimensionsException {
		AbstractMatrix.checkPairwiseCompatibility(this, other);
		var res = new ComplexMatrix(this);
		return this._add(res, other);
	}

	private AbstractGenericMatrix<Complex> _add(
			AbstractGenericMatrix<Complex> lhs,
			AbstractGenericMatrix<Complex> rhs) {
		for (int i = 0; i < lhs._nRow; ++i) {
			for (int j = 0; j < lhs._nCol; ++j) {
				lhs.data[i][j].addeq(rhs.data[i][j]);
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
	public AbstractGenericMatrix<Complex> subeq(AbstractGenericMatrix<Complex> other)
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
	public AbstractGenericMatrix<Complex> sub(AbstractGenericMatrix<Complex> other)
			throws PairwiseIncompatibleDimensionsException {
		AbstractMatrix.checkPairwiseCompatibility(this, other);
		var res = new ComplexMatrix(this);
		return this._sub(res, other);
	}

	private AbstractGenericMatrix<Complex> _sub(
			AbstractGenericMatrix<Complex> lhs,
			AbstractGenericMatrix<Complex> rhs) {
		for (int i = 0; i < lhs._nRow; ++i) {
			for (int j = 0; j < lhs._nCol; ++j) {
				lhs.data[i][j].subeq(rhs.data[i][j]);
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
	public AbstractGenericMatrix<Complex> mul(
			AbstractGenericMatrix<Complex> lhs,
			AbstractGenericMatrix<Complex> rhs)
			throws InternalIncompatibleDimensionsException {
		AbstractMatrix.checkInternalCompatibility(lhs, rhs);
		var res = new ComplexMatrix(lhs._nRow, rhs._nCol);
		for (int i = 0; i < lhs._nRow; i++) {
			for (int j = 0; j < rhs._nCol; j++) {
				for (int k = 0; k < rhs._nRow; k++) {
					res.data[i][j].addeq(lhs.data[i][k].mul(rhs.data[k][j]));
				}
			}
		}
		return res;
	}

	/*
	 * Returns new matrix in a triangle form
	 */
	protected ComplexMatrix triangleForm() {
		var ret = new ComplexMatrix(this);

		for (int i = 0; i < this._nRow; ++i) {
			for (int j = i + 1; j < this._nRow; ++j) {

				var tmp = ret.data[j][i].div(ret.data[i][i]);
				for (int k = 0; k < this._nRow; ++k) {
					ret.data[j][k].subeq(ret.data[i][k].mul(tmp));
				}
			}
		}

		return ret;
	}

	/**
	 * Determinant
	 *
	 * @return new Complex
	 * @throws NonSquareMatrix if matrix is not square-like
	 */
	@Override
	public Complex getDeterminant() throws NonSquareMatrix {
		AbstractMatrix.checkSquareness(this);
		var triangleForm = this.triangleForm();

		var ret = new Complex(1, 0);
		for (int i = 0; i < this._nRow; ++i) {
			ret.muleq(triangleForm.data[i][i]);
		}
		return ret;
	}
}
