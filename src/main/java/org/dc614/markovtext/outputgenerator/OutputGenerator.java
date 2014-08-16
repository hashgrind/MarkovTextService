package org.dc614.markovtext.outputgenerator;

import org.dc614.markovtext.Tuple;
import org.dc614.markovtext.tuplefactory.StringTupleFactory;
import org.dc614.markovtext.tuplepool.TuplePool;

public abstract class OutputGenerator {
	protected TuplePool mTuplePool;
	protected StringTupleFactory mStringTupleFactory;

	public final void generateOutput(int tokens) {
		Tuple firstTuple = mTuplePool.getRandomTuple();

		for (String key : firstTuple.getKeys()) {
			this.appendStringValue(key);
		}

		int tokenCount = 0;
		Tuple nextTuple = firstTuple;
		firstTuple = null;

		while (tokenCount++ < tokens) {
			nextTuple = mStringTupleFactory.getTuple(nextTuple);

			this.appendStringValue(nextTuple.getKeys()[nextTuple.getKeys().length - 1]);

			if (nextTuple == null) {
				break;
			}
		}
	}

	protected abstract void appendStringValue(String value);

	public abstract String getGeneratedOutput();
}
