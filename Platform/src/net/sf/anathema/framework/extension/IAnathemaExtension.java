package net.sf.anathema.framework.extension;

import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.reflections.AnnotationFinder;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.DataFileProvider;

public interface IAnathemaExtension {

  void initialize(DataFileProvider dataFileProvider, AnnotationFinder finder, ResourceLoader loader) throws InitializationException;
}