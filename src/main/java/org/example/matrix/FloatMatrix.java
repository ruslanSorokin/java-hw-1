package org.example.matrix;

import java.util.Arrays;

import org.example.matrix.exception.InternalIncompatibleDimensionsException;
import org.example.matrix.exception.NegativeDimensionException;
import org.example.matrix.exception.NonSquareMatrix;
import org.example.matrix.exception.PairwiseIncompatibleDimensionsException;

public class FloatMatrix extends AbstractGenericMatrix<Double> {

	/*
	 * Constructs a new FloatMatrix with size of the `source` array
	 */
	public FloatMatrix(Double[][] source) {
		super(source.length, source[0].length);
		this.data = Arrays.copyOf(source, source.length);
	}

	/*
	 * Constructs a new FloatMatrix with size `nRow` x `nCol`
	 * throws NegativeDimensionException if any of dimension is negative
	 */
	public FloatMatrix(int nRow, int nCol)
			throws NegativeDimensionException {
		this(nRow, nCol, 0d);
	}

	/*
	 * Constructs a new FloatMatrix with size `nRow` x `nCol` and fills everething
	 * with value of `val`
	 * throws NegativeDimensionException if any of dimension is negative
	 */
	public FloatMatrix(int nRow, int nCol, Double val)
			throws NegativeDimensionException {
		super(nRow, nCol);
		this.data = new Double[this._nRow][this._nCol];
		this.fill(val);
	}

	/*
	 * Constructs a new FloatMatrix with size `nRow` x `nCol` and fills everething
	 * with value of `val`
	 * throws NegativeDimensionException if any of dimension is negative
	 */
	public FloatMatrix(int nRow, int nCol, Float val)
			throws NegativeDimensionException {
		super(nRow, nCol);
		this.data = new Double[this._nRow][this._nCol];
		this.fill(Double.valueOf(val));
	}

	/*
	 * Constructs a new FloatMatrix with size `nRow` x `nCol`
	 */
	public FloatMatrix(FloatMatrix other) {
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
	public AbstractGenericMatrix<Double> addeq(AbstractGenericMatrix<Double> other)
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
	public AbstractGenericMatrix<Double> add(AbstractGenericMatrix<Double> other)
			throws PairwiseIncompatibleDimensionsException {
		AbstractMatrix.checkPairwiseCompatibility(this, other);
		var res = new FloatMatrix(this);
		return this._add(res, other);
	}

	private AbstractGenericMatrix<Double> _add(
			AbstractGenericMatrix<Double> lhs,
			AbstractGenericMatrix<Double> rhs) {
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
	public AbstractGenericMatrix<Double> subeq(AbstractGenericMatrix<Double> other)
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
	public AbstractGenericMatrix<Double> sub(AbstractGenericMatrix<Double> other)
			throws PairwiseIncompatibleDimensionsException {
		AbstractMatrix.checkPairwiseCompatibility(this, other);
		var res = new FloatMatrix(this);
		return this._sub(res, other);
	}

	private AbstractGenericMatrix<Double> _sub(
			AbstractGenericMatrix<Double> lhs,
			AbstractGenericMatrix<Double> rhs) {
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
	public AbstractGenericMatrix<Double> mul(
			AbstractGenericMatrix<Double> other)
			throws InternalIncompatibleDimensionsException {
		AbstractMatrix.checkInternalCompatibility(this, other);
		var res = new FloatMatrix(this._nRow, other._nCol);
		for (int i = 0; i < res._nRow; i++) {
			for (int j = 0; j < res._nCol; j++) {
				for (int k = 0; k < this._nCol; k++) {
					res.data[i][j] += this.data[i][k] * other.data[k][j];
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
	public AbstractGenericMatrix<Double> transposeeq()
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
	public AbstractGenericMatrix<Double> transpose()
			throws NonSquareMatrix {
		AbstractMatrix.checkSquareness(this);
		return this._transpose(new FloatMatrix(this));
	}

	/*
	 * Returns new matrix in a triangle form
	 */
	protected FloatMatrix triangleForm() {
		var ret = new FloatMatrix(this);

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
	 * @return new Double
	 * @throws NonSquareMatrix if matrix is not square-like
	 */
	@Override
	public Double getDeterminant() throws NonSquareMatrix {
		AbstractMatrix.checkSquareness(this);
		var triangleForm = this.triangleForm();

		var ret = 1d;
		for (int i = 0; i < this._nRow; ++i) {
			ret *= triangleForm.data[i][i];
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

		var other = (FloatMatrix) obj;
		return (Arrays.deepEquals(this.data, other.data));
	}

}
