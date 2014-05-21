package net.sf.anathema.hero.template;

import net.sf.anathema.character.framework.type.CharacterType;
import net.sf.anathema.lib.util.Identifier;
import org.apache.commons.lang3.builder.EqualsBuilder;

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

  @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
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