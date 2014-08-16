package org.dc614.markovtext;

import org.dc614.markovtext.outputgenerator.OutputGenerator;
import org.dc614.markovtext.outputgenerator.StringBuilderOutputGenerator;
import org.dc614.markovtext.tuplefactory.SimpleStringTupleFactory;
import org.dc614.markovtext.tuplefactory.StringTupleFactory;
import org.dc614.markovtext.tuplefactory.bulkprocessor.BufferedReaderFileProcessor;
import org.dc614.markovtext.tuplefactory.bulkprocessor.FileProcessor;
import org.dc614.markovtext.tuplepool.HashTuplePool;
import org.dc614.markovtext.tuplepool.TuplePool;
import org.dc614.markovtext.valueselection.RandomValueSelectionAlgorithm;

import java.io.File;
import java.io.IOException;

public class StaticMarkov {
	public static void main(String... args) {
		TuplePool tuplePool = new HashTuplePool(new RandomValueSelectionAlgorithm());
		StringTupleFactory stringTupleFactory = new SimpleStringTupleFactory(2, tuplePool);

		FileProcessor fileProcessor = new BufferedReaderFileProcessor();
		try {
			fileProcessor.processFile(new File("/path/to/your/markov.txt"), stringTupleFactory);
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}

		OutputGenerator outputGenerator = new StringBuilderOutputGenerator(tuplePool, stringTupleFactory);
		outputGenerator.generateOutput(32);
		System.out.println(outputGenerator.getGeneratedOutput());

		System.exit(0);
	}
}
