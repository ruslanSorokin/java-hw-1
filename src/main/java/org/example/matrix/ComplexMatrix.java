package org.example.matrix;

import org.example.matrix.exception.PairwiseIncompatibleDimensionsException;
import org.example.matrix.exception.InternalIncompatibleDimensionsException;
import org.example.matrix.exception.NegativeDimensionException;
import org.example.matrix.exception.NonSquareMatrix;

import java.util.Arrays;

import org.example.complex.Complex;

public class ComplexMatrix
		extends AbstractGenericMatrix<Complex> {

	/*
	 * Constructs a new ComplexMatrix with size of the `source` array
	 */
	public ComplexMatrix(Complex[][] source) {
		super(source.length, source[0].length);
		this.data = Arrays.copyOf(source, source.length);
	}

	/*
	 * Constructs a new ComplexMatrix with size `nRow` x `nCol`
	 * throws NegativeDimensionException if any of dimension is negative
	 */
	public ComplexMatrix(int nRow, int nCol)
			throws NegativeDimensionException {
		this(nRow, nCol, new Complex(0));
	}

	/*
	 * Constructs a new ComplexMatrix with size `nRow` x `nCol` and fills everething
	 * with value of `val`
	 * throws NegativeDimensionException if any of dimension is negative
	 */
	public ComplexMatrix(int nRow, int nCol, Complex val)
			throws NegativeDimensionException {
		super(nRow, nCol);
		this.data = new Complex[this._nRow][this._nCol];
		this.fill(val);
	}

	/*
	 * Constructs a new ComplexMatrix with size `nRow` x `nCol`
	 */
	public ComplexMatrix(ComplexMatrix other) {
		super(other._nRow, other._nCol);
		this.data = new Complex[this._nRow][this._nCol];
		copy(other, this);
	}

	/**
	 * @param src matrix from which elements will be copied
	 * @param dst matrix into which elements will be copied
	 */
	protected void copy(ComplexMatrix src, ComplexMatrix dst) {
		for (int i = 0; i < dst._nRow; ++i) {
			for (int j = 0; j < dst._nCol; ++j) {
				dst.data[i][j] = new Complex(src.data[i][j]);
			}
		}
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
		return _add(this, other);
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
		return _add(new ComplexMatrix(this), other);
	}

	private AbstractGenericMatrix<Complex> _add(
			AbstractGenericMatrix<Complex> lhs,
			AbstractGenericMatrix<Complex> rhs) {
		for (int i = 0; i < lhs._nRow; ++i) {
			for (int j = 0; j < lhs._nCol; ++j) {
				lhs.data[i][j] = lhs.data[i][j].add(rhs.data[i][j]);
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
		return _sub(this, other);
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
		return _sub(new ComplexMatrix(this), other);
	}

	private AbstractGenericMatrix<Complex> _sub(
			AbstractGenericMatrix<Complex> lhs,
			AbstractGenericMatrix<Complex> rhs) {
		for (int i = 0; i < lhs._nRow; ++i) {
			for (int j = 0; j < lhs._nCol; ++j) {
				lhs.data[i][j] = lhs.data[i][j].sub(rhs.data[i][j]);
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
			AbstractGenericMatrix<Complex> other)
			throws InternalIncompatibleDimensionsException {
		AbstractMatrix.checkInternalCompatibility(this, other);
		var res = new ComplexMatrix(this._nRow, other._nCol, new Complex(0));
		for (int i = 0; i < res._nRow; i++) {
			for (int j = 0; j < res._nCol; j++) {
				for (int k = 0; k < this._nCol; k++) {
					res.data[i][j] = res.data[i][j].add(this.data[i][k].mul(other.data[k][j]));
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
	public AbstractGenericMatrix<Complex> transposeeq()
			throws NonSquareMatrix {
		AbstractMatrix.checkSquareness(this);
		return _transpose(this);
	}

	/**
	 * Transpose operation
	 *
	 * @return new matrix
	 * @throws NonSquareMatrix if matrix is not square-like
	 */
	@Override
	public AbstractGenericMatrix<Complex> transpose()
			throws NonSquareMatrix {
		AbstractMatrix.checkSquareness(this);
		return _transpose(new ComplexMatrix(this));
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
					ret.data[j][k] = ret.data[j][k].sub(ret.data[i][k].mul(tmp));
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

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj.getClass() != this.getClass()) {
			return false;
		}

		var other = (ComplexMatrix) obj;
		return (Arrays.deepEquals(this.data, other.data));
	}

}
