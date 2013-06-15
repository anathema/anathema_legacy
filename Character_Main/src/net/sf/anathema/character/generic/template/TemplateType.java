package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public final class TemplateType implements ITemplateType {

  public static final Identifier DEFAULT_SUB_TYPE = new SimpleIdentifier("TemplateType.Default");

  private final ICharacterType characterType;
  private final Identifier subType;

  public TemplateType(ICharacterType characterType) {
    this(characterType, DEFAULT_SUB_TYPE);
  }

  public TemplateType(ICharacterType characterType, Identifier subType) {
    this.characterType = characterType;
    this.subType = subType;
  }

  @Override
  public ICharacterType getCharacterType() {
    return characterType;
  }

  @Override
  public Identifier getSubType() {
    return subType;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof TemplateType)) {
      return false;
    }
    ITemplateType otherType = (ITemplateType) obj;
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