package net.sf.anathema.framework.extension;

import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.initialization.reflections.AnathemaReflections;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.resources.IResourceDataManager;

public interface IAnathemaExtension {

  void initialize(IResourceDataManager resourceDataManager,
		  IDataFileProvider dataFileProvider,
		  AnathemaReflections reflections,
		  Instantiater instantiater) throws InitializationException;
}