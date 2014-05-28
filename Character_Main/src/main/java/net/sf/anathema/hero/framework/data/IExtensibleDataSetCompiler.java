package net.sf.anathema.hero.framework.data;

import net.sf.anathema.framework.environment.resources.ResourceFile;

public interface IExtensibleDataSetCompiler {
	String getName();
	
	String getRecognitionPattern();
	
	void registerFile(ResourceFile resource) throws Exception;
	
	ExtensibleDataSet build();
}