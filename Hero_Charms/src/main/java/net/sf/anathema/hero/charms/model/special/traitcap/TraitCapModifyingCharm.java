package net.sf.anathema.hero.charms.model.special.traitcap;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.charms.model.special.ISpecialCharmVisitor;
import net.sf.anathema.hero.charms.model.special.traitcap.ITraitCapModifyingCharm;

public class TraitCapModifyingCharm implements ITraitCapModifyingCharm {
  private final String charmId;
  private final TraitType traitType;
  private final int modifier;

  public TraitCapModifyingCharm(String charmId, TraitType trait, int modifier) {
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

  @Override
  public TraitType getTraitType() {
    return traitType;
  }

  @Override
  public int getModifier() {
    return modifier;
  }

  public String toString() {
    return "[" + getCharmId() + ";mod " + traitType.getId() + "]";
  }
}