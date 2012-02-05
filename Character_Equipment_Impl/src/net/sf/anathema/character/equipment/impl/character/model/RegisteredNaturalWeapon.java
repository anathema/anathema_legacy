package net.sf.anathema.character.equipment.impl.character.model;

import net.sf.anathema.character.generic.type.CharacterType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RegisteredNaturalWeapon {

  CharacterType characterType();
}
