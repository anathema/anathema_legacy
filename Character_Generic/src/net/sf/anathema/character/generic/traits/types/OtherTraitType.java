package net.sf.anathema.character.generic.traits.types;

import net.sf.anathema.character.generic.traits.ITraitType;

public enum OtherTraitType implements ITraitType {
  Essence, Willpower, Specialty, OxBodyTechnique, SpecialCharm;

  public String getId() {
    return name();
  }

  public void accept(ITraitTypeVisitor visitor) {
    if (this == Essence) {
      visitor.visitEssence(this);
    }
    if (this == Willpower) {
      visitor.visitWillpower(this);
    }
    // TODO: Sinnvolle Behandlung finden
    if (this == Specialty) {
      throw new UnsupportedOperationException("Specialties must not be visited"); //$NON-NLS-1$
    }
  }

  @Override
  public String toString() {
    return getId();
  }
}