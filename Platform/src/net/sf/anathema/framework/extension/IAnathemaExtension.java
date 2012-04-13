package net.sf.anathema.framework.extension;

import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.resources.IResourceCollection;

public interface IAnathemaExtension {

  void initialize(IResourceCollection resources,
		  IDataFileProvider dataFileProvider,
		  Instantiater instantiater) throws InitializationException;
}