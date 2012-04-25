package net.sf.anathema.framework.extension;

import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.reflections.AnnotationFinder;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.resources.IResources;

public interface IAnathemaExtension {

  void initialize(IResources resources, IDataFileProvider dataFileProvider, AnnotationFinder finder,
                  ResourceLoader loader) throws InitializationException;
}