package org.dc614.markovtext;

import org.dc614.markovtext.valueselection.ValueSelectionAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class Tuple {
	private String[] mKeys;

	public String[] getKeys () {
		return mKeys;
	}

	private List<String> mValues;
	private ValueSelectionAlgorithm mValueSelectionAlgorithm;

	private String mSelectedValue = null;

	// Prevent direct instantiation
	private Tuple() {}

	public void addValue(String value) {
		this.mValues.add(value);
	}

	public String getValue() {
		if (mSelectedValue == null) {
			mSelectedValue = mValueSelectionAlgorithm.getValue(mValues);
		}

		return mSelectedValue;
	}

	public void resetGottenValue() {
		mSelectedValue = null;
	}

	public String[] getNextKeys() {
		String[] keyTemplate = new String[mKeys.length];

		for (int count = 1; count < mKeys.length; count++) {
			keyTemplate[count - 1] = mKeys[count];
		}

		keyTemplate[mKeys.length - 1] = this.getValue();

		return keyTemplate;
	}

	@Override
	public String toString() {
		String returnString = new String();

		for (String key : mKeys) {
			returnString += key + "; ";
		}

		returnString += " - ";

		for (String value : mValues) {
			returnString += value + "; ";
		}

		return returnString;
	}

	public static class TupleBuilder {
		private String[] mKeys;
		private List<String> mValues;
		private ValueSelectionAlgorithm mValueSelectionAlgorithm;

		public TupleBuilder keys(String[] keys) {
			this.mKeys = keys;

			return this;
		}

		public TupleBuilder value(String value) {
			if (this.mValues == null) {
				synchronized (TupleBuilder.class) {
					if (this.mValues == null) {
						this.mValues = new ArrayList<>();
					}
				}
			}

			this.mValues.add(value);

			return this;
		}

		public TupleBuilder valueSelectionAlgorithm(ValueSelectionAlgorithm valueSelectionAlgorithm) {
			mValueSelectionAlgorithm = valueSelectionAlgorithm;

			return this;
		}

		public Tuple build() {
			Tuple tuple = new Tuple();

			tuple.mKeys = this.mKeys;
			tuple.mValues = this.mValues;
			tuple.mValueSelectionAlgorithm = this.mValueSelectionAlgorithm;

			return tuple;
		}
	}

}
