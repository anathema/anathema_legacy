package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.util.Identifier;

public final class TemplateType implements ITemplateType {

  public static final Identified DEFAULT_SUB_TYPE = new Identifier("TemplateType.Default"); //$NON-NLS-1$

  private final ICharacterType characterType;
  private final Identified subType;

  public TemplateType(ICharacterType characterType) {
    this(characterType, DEFAULT_SUB_TYPE);
  }

  public TemplateType(ICharacterType characterType, Identified subType) {
    this.characterType = characterType;
    this.subType = subType;
  }

  @Override
  public ICharacterType getCharacterType() {
    return characterType;
  }

  @Override
  public Identified getSubType() {
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