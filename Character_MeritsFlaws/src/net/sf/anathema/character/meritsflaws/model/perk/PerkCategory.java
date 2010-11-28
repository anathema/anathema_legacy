package net.sf.anathema.character.meritsflaws.model.perk;

import net.sf.anathema.lib.util.IIdentificate;

public enum PerkCategory implements IIdentificate {
  Mental, Physical, Property, Social, Supernatural;
  public String getId() {
    return name();
  }
}