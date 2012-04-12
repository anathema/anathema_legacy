package net.sf.anathema.framework.extension;

import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.resources.IResourceData;

public interface IAnathemaExtension {

  void initialize(IResourceData resourceData, IDataFileProvider dataFileProvider, Instantiater instantiater) throws InitializationException;
}