package net.sf.anathema.framework.extension;

import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.DataFileProvider;

public interface IAnathemaExtension {

  void initialize(DataFileProvider dataFileProvider, ObjectFactory instantiater, ResourceLoader loader) throws InitializationException;
}