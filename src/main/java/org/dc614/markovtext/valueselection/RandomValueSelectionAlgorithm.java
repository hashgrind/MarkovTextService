package org.dc614.markovtext.valueselection;

import java.util.List;
import java.util.Random;

public class RandomValueSelectionAlgorithm implements ValueSelectionAlgorithm {
	private static final Random RANDOM = new Random();

	@Override
	public String getValue(List<String> values) {
		return values.get(RANDOM.nextInt(values.size()));
	}
}
