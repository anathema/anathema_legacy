package net.sf.anathema.character.main.model;

import net.sf.anathema.character.main.model.CharacterModelGroup;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModelGroup {

  CharacterModelGroup group() default CharacterModelGroup.Miscellaneous;
}
