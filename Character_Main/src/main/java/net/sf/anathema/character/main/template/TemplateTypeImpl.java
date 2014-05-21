package net.sf.anathema.character.main.template;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.lib.util.Identifier;

public final class TemplateTypeImpl implements TemplateType {

  private final CharacterType characterType;
  private final Identifier subType;

  public TemplateTypeImpl(CharacterType characterType, Identifier subType) {
    this.characterType = characterType;
    this.subType = subType;
  }

  @Override
  public CharacterType getCharacterType() {
    return characterType;
  }

  @Override
  public Identifier getSubType() {
    return subType;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof TemplateTypeImpl)) {
      return false;
    }
    TemplateType otherType = (TemplateType) obj;
    return getCharacterType().getId().equals(otherType.getCharacterType().getId())
        && getSubType().getId().equals(otherType.getSubType().getId());
  }
  
  @Override
  public String toString() {
	  return characterType.getId() + ", " + subType.getId();
  }

  @Override
  public int hashCode() {
    return characterType.getId().hashCode();
  }
}