package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.magic.charms.special.ITraitCapModifyingCharm;
import net.sf.anathema.character.generic.traits.ITraitType;

public class TraitCapModifyingCharm implements ITraitCapModifyingCharm {
  private final String charmId;
  private final ITraitType traitType;
  private final int modifier;

  public TraitCapModifyingCharm(String charmId, ITraitType trait, int modifier) {
    this.charmId = charmId;
    this.traitType = trait;
    this.modifier = modifier;
  }

  @Override
  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitTraitCapModifyingCharm(this);
  }

  @Override
  public String getCharmId() {
    return charmId;
  }

  public ITraitType getTraitType() {
    return traitType;
  }

  public int getModifier() {
    return modifier;
  }

  public String toString() {
    return "[" + getCharmId() + ";mod " + traitType.getId() + "]";
  }
}
