package net.sf.anathema.framework.extension;

import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.ResourceLoader;
import net.sf.anathema.initialization.repository.DataFileProvider;

public interface IAnathemaExtension {

  void initialize(DataFileProvider dataFileProvider, ObjectFactory instantiater, ResourceLoader loader);
}