package net.sf.anathema.character.generic.traits.types;

import net.sf.anathema.character.generic.traits.ITraitType;

public enum AbilityType implements ITraitType {
  Archery,
  MartialArts,
  Melee,
  Thrown,
  War,
  Integrity,
  Performance,
  Presence,
  Resistance,
  Survival,
  Craft,
  Investigation,
  Lore,
  Medicine,
  Occult,
  Athletics,
  Awareness,
  Dodge,
  Larceny,
  Stealth,
  Bureaucracy,
  Linguistics,
  Ride,
  Sail,
  Socialize;

  @Override
  public void accept(ITraitTypeVisitor visitor) {
    visitor.visitAbility(this);
  }

  @Override
  public String getId() {
    return name();
  }
}