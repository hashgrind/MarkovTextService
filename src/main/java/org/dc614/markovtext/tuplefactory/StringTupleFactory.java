package org.dc614.markovtext.tuplefactory;

import org.dc614.markovtext.Tuple;
import org.dc614.markovtext.tuplepool.TuplePool;

public abstract class StringTupleFactory {
	protected TuplePool mTuplePool;

	public TuplePool getTuplePool() {
		return mTuplePool;
	}

	protected int mKeyLength;

	public int getKeyLength() {
		return mKeyLength;
	}

	public abstract void processString(String input);

	public abstract Tuple getTuple(Tuple previousTuple);
}
