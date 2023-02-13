package org.example.matrix;

import org.example.matrix.exception.PairwiseIncompatibleDimensionsException;
import org.example.matrix.exception.InternalIncompatibleDimensionsException;
import org.example.matrix.exception.NegativeDimensionException;
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
	public AbstractGenericMatrix<Complex> add(AbstractGenericMatrix<Complex> other)
			throws PairwiseIncompatibleDimensionsException {
		AbstractMatrix.checkPairwiseCompatibility(this, other);

		for (int i = 0; i < this._nRow; ++i) {
			for (int j = 0; j < this._nCol; ++j) {
				this.data[i][j] = Complex.add(this.data[i][j], other.data[i][j]);
			}
		}
		return this;
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
	public AbstractGenericMatrix<Complex> sub(AbstractGenericMatrix<Complex> other)
			throws PairwiseIncompatibleDimensionsException {
		AbstractMatrix.checkPairwiseCompatibility(this, other);

		for (int i = 0; i < this._nRow; ++i) {
			for (int j = 0; j < this._nCol; ++j) {
				this.data[i][j] = Complex.sub(this.data[i][j], other.data[i][j]);
			}
		}
		return this;
	}

	/**
	 * Multiplication operation equals `this * other`
	 *
	 * @param other matrix by which `this` matrix will be multiplied
	 * @return new matrix
	 * @throws InternalIncompatibleDimensionsException if matrices are not internal
	 *                                                 compatible
	 */
	public AbstractGenericMatrix<Complex> mul(
			AbstractGenericMatrix<Complex> lhs,
			AbstractGenericMatrix<Complex> rhs)
			throws InternalIncompatibleDimensionsException {
		AbstractMatrix.checkInternalCompatibility(lhs, rhs);
		var res = new ComplexMatrix(lhs._nRow, rhs._nCol);
		return _mul(lhs, rhs, res);
	}

	private static AbstractGenericMatrix<Complex> _mul(
			AbstractGenericMatrix<Complex> lhs,
			AbstractGenericMatrix<Complex> rhs,
			AbstractGenericMatrix<Complex> res)
			throws InternalIncompatibleDimensionsException {

		for (int i = 0; i < lhs._nRow; i++) {
			for (int j = 0; j < rhs._nCol; j++) {
				for (int k = 0; k < rhs._nRow; k++) {
					res.data[i][j].add(Complex.mul(lhs.data[i][k], rhs.data[k][j]));
				}
			}
		}
		return res;
	}

}
