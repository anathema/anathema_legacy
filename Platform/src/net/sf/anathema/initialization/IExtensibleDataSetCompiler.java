package net.sf.anathema.initialization;

import net.sf.anathema.lib.resources.IExtensibleDataSet;

public interface IExtensibleDataSetCompiler {
	String getName();
	
	String getRecognitionPattern();
	
	String getSplashStatusString();
	
	void registerFile(String fileName) throws Exception;
	
	IExtensibleDataSet build();
}
