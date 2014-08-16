package org.dc614.markovtext.outputgenerator;

import com.google.inject.Inject;
import org.dc614.markovtext.tuplefactory.StringTupleFactory;
import org.dc614.markovtext.tuplepool.TuplePool;

public class StringBuilderOutputGenerator extends OutputGenerator {
	private final StringBuilder mOutputStringBuilder = new StringBuilder();

	@Inject
	public StringBuilderOutputGenerator(TuplePool tuplePool, StringTupleFactory stringTupleFactory) {
		mTuplePool = tuplePool;
		mStringTupleFactory = stringTupleFactory;
	}

	@Override
	protected void appendStringValue(String value) {
		mOutputStringBuilder.append(value + " ");
	}

	@Override
	public String getGeneratedOutput() {
		return mOutputStringBuilder.toString();
	}
}
