package net.sf.anathema.character.reporting.pdf.content;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.type.ICharacterType;

public class BasicContent implements SubContent {

  private IGenericCharacter character;

  public BasicContent(IGenericCharacter character) {
    this.character = character;
  }

  public boolean isEssenceUser() {
    return getCharacterType().isEssenceUser();
  }

  private ICharacterType getCharacterType() {
    return character.getTemplate().getTemplateType().getCharacterType();
  }

  public boolean favorsTraitType(FavoringTraitType type) {
    return getCharacterType().getFavoringTraitType().equals(type);
  }

  public boolean isOfType(ICharacterType type) {
    return getCharacterType().equals(type);
  }

  public IAdditionalModel getAdditionalModel(String id) {
    return character.getAdditionalModel(id);
  }

  @Override
  public boolean hasContent() {
    return true;
  }
}
