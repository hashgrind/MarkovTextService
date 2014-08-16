package org.dc614.markovtext.tuplepool;

import com.google.inject.Inject;
import org.dc614.markovtext.Tuple;
import org.dc614.markovtext.valueselection.ValueSelectionAlgorithm;

import java.util.*;

public class HashTuplePool extends TuplePool {
	private volatile Map<String[], Tuple> mKeyTuplesMap = new HashMap<>();
	private Tuple mFirstTuple = null;
	private Tuple mLastTuple = null;

	@Inject
	public HashTuplePool(ValueSelectionAlgorithm valueSelectionAlgorithm) {
		mValueSelectionAlgorithm = valueSelectionAlgorithm;
	}

	@Override
	public synchronized void addTuple(String[] keys, String value) {
		Tuple tuple = null;

		if (mKeyTuplesMap.keySet().contains(keys)) {
			tuple = mKeyTuplesMap.get(tuple);

			tuple.addValue(value);
		} else {
			tuple = new Tuple.TupleBuilder()
					.keys(keys)
					.value(value)
					.valueSelectionAlgorithm(mValueSelectionAlgorithm)
					.build();

			mKeyTuplesMap.put(keys, tuple);
		}
	}

	@Override
	public Tuple getTuple(String[] keys) {
		for (String[] candidateKey : mKeyTuplesMap.keySet()) {
			if (Arrays.equals(candidateKey, keys)) {
				return mKeyTuplesMap.get(candidateKey);
			}
		}

		return getRandomTuple();
	}

	@Override
	public Tuple getRandomTuple() {
		if (mKeyTuplesMap.keySet().size() < 1) {
			return null;
		}

		List<String[]> keyList = new ArrayList<>(mKeyTuplesMap.keySet());
		Collections.shuffle(keyList);

		return getTuple(keyList.get(0));
	}

	@Override
	public int getPoolSize() {
		return mKeyTuplesMap.keySet().size();
	}
}
