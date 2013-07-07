package net.sf.anathema.hero.template;

import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.hero.model.Hero;

public class NativeCharacterType {

  public static ICharacterType get(Hero hero){
    return hero.getTemplate().getTemplateType().getCharacterType();
  }
}