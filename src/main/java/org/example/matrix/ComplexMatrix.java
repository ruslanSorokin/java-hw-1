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
	 * @param other matrix which will be added to `this` matrix
	 * @return new AbstractGenericMatrix<Complex>
	 * @throws PairwiseIncompatibleDimensionsException if matrices are not pairwise
	 *                                                 compatible
	 */
	@Override
	public AbstractGenericMatrix<Complex> add(AbstractGenericMatrix<Complex> other)
			throws PairwiseIncompatibleDimensionsException {
		AbstractMatrix.checkPairwiseCompatibility(this, other);

		var ret = new ComplexMatrix(this._nRow, this._nCol);

		for (int i = 0; i < this._nRow; ++i) {
			for (int j = 0; j < this._nCol; ++j) {
				ret.data[i][j] = Complex.add(this.data[i][j], other.data[i][j]);
			}
		}
		return ret;
	}

	/**
	 * @param other matrix by which `this` matrix will be divided
	 * @return new AbstractGenericMatrix<Complex>
	 * @throws PairwiseIncompatibleDimensionsException if matrices are not pairwise
	 *                                                 compatible
	 */
	@Override
	public AbstractGenericMatrix<Complex> sub(AbstractGenericMatrix<Complex> other)
			throws PairwiseIncompatibleDimensionsException {
		AbstractMatrix.checkPairwiseCompatibility(this, other);

		var ret = new ComplexMatrix(this._nRow, this._nCol);

		for (int i = 0; i < this._nRow; ++i) {
			for (int j = 0; j < this._nCol; ++j) {
				ret.data[i][j] = Complex.sub(this.data[i][j], other.data[i][j]);
			}
		}
		return ret;
	}

	/**
	 * @param other matrix by which `this` matrix will be multiplied
	 * @return new AbstractGenericMatrix<Complex>
	 * @throws InternalIncompatibleDimensionsException if matrices are not internal
	 *                                                 compatible
	 */
	@Override
	public AbstractGenericMatrix<Complex> mul(AbstractGenericMatrix<Complex> other)
			throws InternalIncompatibleDimensionsException {
		AbstractMatrix.checkInternalCompatibility(this, other);

		var ret = new ComplexMatrix(this._nRow, other._nCol);

		for (int i = 0; i < this._nRow; i++) {
			for (int j = 0; j < other._nCol; j++) {
				for (int k = 0; k < other._nRow; k++) {
					ret.data[i][j].add(Complex.mul(this.data[i][k], other.data[k][j]));
				}
			}
		}
		return ret;
	}

}
