package org.dc614.markovtext.tuplefactory.bulkprocessor;

import org.dc614.markovtext.tuplefactory.StringTupleFactory;

import java.io.File;
import java.io.IOException;

public abstract class FileProcessor {
	public abstract void processFile(File file, StringTupleFactory stringTupleFactory) throws IOException;
}
