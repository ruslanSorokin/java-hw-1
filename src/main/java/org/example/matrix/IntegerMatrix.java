package org.example.matrix;

import org.example.matrix.exception.InternalIncompatibleDimensionsException;
import org.example.matrix.exception.NegativeDimensionException;
import org.example.matrix.exception.PairwiseIncompatibleDimensionsException;

public class IntegerMatrix extends AbstractGenericMatrix<Long> {

	public IntegerMatrix(int nRow, int nCol)
			throws NegativeDimensionException {
		super(nRow, nCol);
		this.data = new Long[this._nRow][this._nCol];
		this.fill(0l);
	}

	public IntegerMatrix(int nRow, int nCol, Long val)
			throws NegativeDimensionException {
		this(nRow, nCol);
		this.fill(val);
	}

	public IntegerMatrix(int nRow, int nCol, Integer val)
			throws NegativeDimensionException {
		this(nRow, nCol);
		this.fill(Long.valueOf(val));
	}

	public IntegerMatrix(IntegerMatrix other) {
		this(other._nRow, other._nCol);
		this.copy(other, this);
	}

	@Override
	public AbstractGenericMatrix<Long> add(AbstractGenericMatrix<Long> other)
			throws PairwiseIncompatibleDimensionsException {
		AbstractMatrix.checkPairwiseCompatibility(this, other);

		var ret = new IntegerMatrix(this._nRow, this._nCol);

		for (int i = 0; i < this._nRow; ++i) {
			for (int j = 0; j < this._nCol; ++j) {
				ret.data[i][j] = this.data[i][j] + other.data[i][j];
			}
		}
		return ret;
	}

	@Override
	public AbstractGenericMatrix<Long> sub(AbstractGenericMatrix<Long> other)
			throws PairwiseIncompatibleDimensionsException {
		AbstractMatrix.checkPairwiseCompatibility(this, other);

		var ret = new IntegerMatrix(this._nRow, this._nCol);

		for (int i = 0; i < this._nRow; ++i) {
			for (int j = 0; j < this._nCol; ++j) {
				ret.data[i][j] = this.data[i][j] - other.data[i][j];
			}
		}
		return ret;
	}

	@Override
	public AbstractGenericMatrix<Long> mul(AbstractGenericMatrix<Long> other)
			throws InternalIncompatibleDimensionsException {
		AbstractMatrix.checkInternalCompatibility(this, other);

		var ret = new IntegerMatrix(this._nRow, other._nCol);

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
