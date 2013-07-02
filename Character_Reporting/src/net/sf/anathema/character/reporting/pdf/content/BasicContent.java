package net.sf.anathema.character.reporting.pdf.content;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.hero.model.Hero;

public class BasicContent implements SubContent {

  private Hero hero;

  public BasicContent(Hero hero) {
    this.hero = hero;
  }

  public boolean isEssenceUser() {
    return getCharacterType().isEssenceUser();
  }

  private ICharacterType getCharacterType() {
    return hero.getTemplate().getTemplateType().getCharacterType();
  }

  public boolean isOfType(ICharacterType type) {
    return getCharacterType().equals(type);
  }

  @Override
  public boolean hasContent() {
    return true;
  }
}
