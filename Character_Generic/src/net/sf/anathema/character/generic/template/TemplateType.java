package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public final class TemplateType implements ITemplateType {

  public static final IIdentificate DEFAULT_SUB_TYPE = new Identificate("TemplateType.Default"); //$NON-NLS-1$

  private final ICharacterType characterType;
  private final IIdentificate subType;

  public TemplateType(ICharacterType characterType) {
    this(characterType, DEFAULT_SUB_TYPE);
  }

  public TemplateType(ICharacterType characterType, IIdentificate subType) {
    this.characterType = characterType;
    this.subType = subType;
  }

  public ICharacterType getCharacterType() {
    return characterType;
  }

  public IIdentificate getSubType() {
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
  public int hashCode() {
    return characterType.getId().hashCode();
  }
}