package net.sf.anathema.character.presenter.initializers;

import net.sf.anathema.character.main.hero.CharacterModelGroup;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
/**All classes thus annotated must implement CharacterModelInitializer and have a constructor for ApplicationModel as its sole argument.*/
public @interface RegisteredInitializer {

  CharacterModelGroup value();
}