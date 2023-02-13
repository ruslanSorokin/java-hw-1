package org.example.matrix;

import org.example.matrix.exception.InternalIncompatibleDimensionsException;
import org.example.matrix.exception.NegativeDimensionException;
import org.example.matrix.exception.PairwiseIncompatibleDimensionsException;

public class FloatMatrix extends AbstractGenericMatrix<Double> {

	/*
	 * Constructs a new FloatMatrix with size `nRow` x `nCol`
	 * throws NegativeDimensionException if any of dimension is negative
	 */
	public FloatMatrix(int nRow, int nCol)
			throws NegativeDimensionException {
		super(nRow, nCol);
		this.data = new Double[this._nRow][this._nCol];
		this.fill(0d);
	}

	/*
	 * Constructs a new FloatMatrix with size `nRow` x `nCol` and fills everething
	 * with value of `val`
	 * throws NegativeDimensionException if any of dimension is negative
	 */
	public FloatMatrix(int nRow, int nCol, Double val)
			throws NegativeDimensionException {
		this(nRow, nCol);
		this.fill(val);
	}

	/*
	 * Constructs a new FloatMatrix with size `nRow` x `nCol` and fills everething
	 * with value of `val`
	 * throws NegativeDimensionException if any of dimension is negative
	 */
	public FloatMatrix(int nRow, int nCol, Float val)
			throws NegativeDimensionException {
		this(nRow, nCol);
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
	 * @param other matrix which will be added to `this` matrix
	 * @return new AbstractGenericMatrix<Double>
	 * @throws PairwiseIncompatibleDimensionsException if matrices are not pairwise
	 *                                                 compatible
	 */
	@Override
	public AbstractGenericMatrix<Double> add(AbstractGenericMatrix<Double> other)
			throws PairwiseIncompatibleDimensionsException {
		AbstractMatrix.checkPairwiseCompatibility(this, other);

		var ret = new FloatMatrix(this._nRow, this._nCol);

		for (int i = 0; i < this._nRow; ++i) {
			for (int j = 0; j < this._nCol; ++j) {
				ret.data[i][j] = this.data[i][j] + other.data[i][j];
			}
		}
		return ret;
	}

	/**
	 * @param other matrix by which `this` matrix will be divided
	 * @return new AbstractGenericMatrix<Double>
	 * @throws PairwiseIncompatibleDimensionsException if matrices are not pairwise
	 *                                                 compatible
	 */
	@Override
	public AbstractGenericMatrix<Double> sub(AbstractGenericMatrix<Double> other)
			throws PairwiseIncompatibleDimensionsException {
		AbstractMatrix.checkPairwiseCompatibility(this, other);

		var ret = new FloatMatrix(this._nRow, this._nCol);

		for (int i = 0; i < this._nRow; ++i) {
			for (int j = 0; j < this._nCol; ++j) {
				ret.data[i][j] = this.data[i][j] - other.data[i][j];
			}
		}
		return ret;
	}

	/**
	 * @param other matrix by which `this` matrix will be multiplied
	 * @return new AbstractGenericMatrix<Double>
	 * @throws InternalIncompatibleDimensionsException if matrices are not internal
	 *                                                 compatible
	 */
	@Override
	public AbstractGenericMatrix<Double> mul(AbstractGenericMatrix<Double> other)
			throws InternalIncompatibleDimensionsException {
		AbstractMatrix.checkInternalCompatibility(this, other);

		var ret = new FloatMatrix(this._nRow, other._nCol);

		for (int i = 0; i < this._nRow; i++) {
			for (int j = 0; j < other._nCol; j++) {
				for (int k = 0; k < other._nRow; k++) {
					ret.data[i][j] += this.data[i][k] * other.data[k][j];
				}
			}
		}
		return ret;
	}

}
