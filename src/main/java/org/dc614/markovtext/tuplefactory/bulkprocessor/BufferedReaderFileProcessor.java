package org.dc614.markovtext.tuplefactory.bulkprocessor;

import org.dc614.markovtext.tuplefactory.StringTupleFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderFileProcessor extends FileProcessor {
	@Override
	public void processFile(File file, StringTupleFactory stringTupleFactory) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

		String readLine;

		while ((readLine = bufferedReader.readLine()) != null) {
			if (readLine.length() < 1) {
				continue;
			}

			stringTupleFactory.processString(readLine);
		}

		bufferedReader.close();
	}
}
