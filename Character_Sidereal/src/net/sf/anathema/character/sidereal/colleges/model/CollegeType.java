package net.sf.anathema.character.sidereal.colleges.model;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;

public enum CollegeType implements ITraitType {

  Captain, Gull, Mast, Messenger, ShipsWheel, Ewer, Lovers, Musician, Peacock, Pillar, Banner, Gauntlet, Quiver,
  Shield, Spear, Guardians, Key, Mask, Sorcerer, TreasureTrove, Corpse, Crow, Haywain, RisingSmoke, Sword;

  public String getId() {
    return name();
  }

  public void accept(ITraitTypeVisitor visitor) {
    visitor.visitCustomTraitType(this);
  }
}