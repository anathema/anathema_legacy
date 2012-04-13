package net.sf.anathema.initialization.reflections;

import java.lang.annotation.Annotation;
import java.util.Set;

import net.sf.anathema.lib.resources.IAnathemaResourceFile;

public interface AnathemaReflections {

  Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation);

  Set<IAnathemaResourceFile> getResourcesMatching(String namepattern);
}