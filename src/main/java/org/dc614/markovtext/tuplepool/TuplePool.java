package org.dc614.markovtext.tuplepool;

import org.dc614.markovtext.Tuple;
import org.dc614.markovtext.valueselection.ValueSelectionAlgorithm;

public abstract class TuplePool {
	protected ValueSelectionAlgorithm mValueSelectionAlgorithm;

	public abstract void addTuple(String[] keys, String value);
	public abstract Tuple getTuple(String[] keys);
	public abstract Tuple getRandomTuple();
	public abstract int getPoolSize();
}
