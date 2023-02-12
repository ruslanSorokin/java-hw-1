package org.example.matrix;

import org.example.matrix.exception.PairwiseIncompatibleDimensionsException;
import org.example.matrix.exception.InternalIncompatibleDimensionsException;
import org.example.matrix.exception.NegativeDimensionException;
import org.example.complex.Complex;

public class ComplexMatrix
		extends AbstractGenericMatrix<Complex> {

	public ComplexMatrix(int nRow, int nCol)
			throws NegativeDimensionException {
		super(nRow, nCol);
		this.data = new Complex[this._nRow][this._nCol];
	}

	public ComplexMatrix(int nRow, int nCol, Complex val)
			throws NegativeDimensionException {
		this(nRow, nCol);
		this.fill(val);
	}

	public ComplexMatrix(ComplexMatrix other) {
		this(other._nRow, other._nCol);
		this.copy(other, this);
	}

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

	@Override
	public AbstractGenericMatrix<Complex> mul(AbstractGenericMatrix<Complex> other)
			throws InternalIncompatibleDimensionsException {
		AbstractMatrix.checkInternalCompatibility(this, other);

		var ret = new ComplexMatrix(this._nRow, this._nCol);
		for (int i = 0; i < this._nRow; i++) {
			for (int j = 0; j < other.getNCol(); j++) {
				for (int k = 0; k < this._nCol; k++) {
					ret.data[i][k].add(Complex.mul(this.data[i][j], other.data[j][k]));
				}
			}
		}
		return ret;
	}

}
