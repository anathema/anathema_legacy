package net.sf.anathema.hero.template;

import net.sf.anathema.character.framework.type.CharacterType;
import net.sf.anathema.hero.model.Hero;

public class NativeCharacterType {

  public static CharacterType get(Hero hero){
    return hero.getTemplate().getTemplateType().getCharacterType();
  }
}