package net.sf.anathema.initialization;

import net.sf.anathema.lib.resources.IAnathemaResourceFile;
import net.sf.anathema.lib.resources.IExtensibleDataSet;

public interface IExtensibleDataSetCompiler {
	String getName();
	
	String getRecognitionPattern();
	
	void registerFile(IAnathemaResourceFile resource) throws Exception;
	
	IExtensibleDataSet build();
}
