package net.sf.anathema.character.generic.framework.reflections;

import java.lang.annotation.Annotation;
import java.util.Set;

public interface CharacterReflections {

  Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation);
}