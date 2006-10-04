package net.sf.anathema.framework.extension;

import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.resources.IResources;

public interface IAnathemaExtension {

  public void initialize(IResources resources, IDataFileProvider dataFileProvider) throws InitializationException;
}