package org.dc614.markovtext.tuplefactory.bulkprocessor;

import org.dc614.markovtext.tuplefactory.StringTupleFactory;

import java.io.File;
import java.io.IOException;

public class BufferedReaderDirectoryProcessor extends FileProcessor {
	@Override
	public void processFile(File file, StringTupleFactory stringTupleFactory) throws IOException {
		FileProcessor fileProcessor;

		for (File subFile : file.listFiles()) {
			fileProcessor = new BufferedReaderFileProcessor();
			fileProcessor.processFile(subFile, stringTupleFactory);
		}
	}
}
