package org.dc614.markovtext.tuplefactory;

import com.google.inject.Inject;
import org.dc614.markovtext.Tuple;
import org.dc614.markovtext.tuplepool.TuplePool;

import java.util.Arrays;
import java.util.List;

public class SimpleStringTupleFactory extends StringTupleFactory {
	@Inject
	public SimpleStringTupleFactory(int keyLength, TuplePool tuplePool) {
		mKeyLength = keyLength;
		mTuplePool = tuplePool;
	}

	@Override
	public void processString(String input) {
		List<String> tokenList = Arrays.asList(input.split(" "));

		while (tokenList.size() >= getKeyLength() + 1) {
			List<String> nextKeysAndValue = tokenList.subList(0, getKeyLength() + 1);
			tokenList = tokenList.subList(1, tokenList.size());

			String[] currentTokens = nextKeysAndValue.subList(0, getKeyLength()).toArray(new String[0]);
			String nextValue = nextKeysAndValue.get(nextKeysAndValue.size() - 1);

			getTuplePool().addTuple(currentTokens, nextValue);
		}
	}

	@Override
	public Tuple getTuple(Tuple previousTuple) {
		Tuple tuple = null;

		if (previousTuple == null) {
			tuple = getTuplePool().getRandomTuple();
		} else {
			previousTuple.resetGottenValue();
			tuple = getTuplePool().getTuple(previousTuple.getNextKeys());
		}

		return tuple;
	}
}
