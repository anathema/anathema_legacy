package net.sf.anathema.lib.resources;

public interface IExtensibleDataSetCompiler {
	String getName();
	
	String getRecognitionPattern();
	
	void registerFile(ResourceFile resource) throws Exception;
	
	IExtensibleDataSet build();
}