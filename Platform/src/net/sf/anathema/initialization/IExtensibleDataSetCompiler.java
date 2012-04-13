package net.sf.anathema.initialization;

import net.sf.anathema.initialization.reflections.IAnathemaResource;
import net.sf.anathema.lib.resources.IExtensibleDataSet;

public interface IExtensibleDataSetCompiler {
	String getName();
	
	String getRecognitionPattern();
	
	String getSplashStatusString();
	
	void registerFile(IAnathemaResource resource) throws Exception;
	
	IExtensibleDataSet build();
}
