package net.sf.anathema.hero.display.presenter;

import net.sf.anathema.hero.display.HeroModelGroup;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
/**All classes thus annotated must implement CharacterModelInitializer and have a constructor for ApplicationModel as its sole argument.*/
public @interface RegisteredInitializer {

  HeroModelGroup value();
}