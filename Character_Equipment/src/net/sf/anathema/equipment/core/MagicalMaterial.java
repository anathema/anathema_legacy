package net.sf.anathema.equipment.core;

import net.sf.anathema.lib.util.Identifier;

public enum MagicalMaterial implements Identifier {
  Orichalcum, Jade, Moonsilver, Starmetal, Soulsteel, Adamant,
  VitriolOrichalcum, VitriolJade, VitriolMoonsilver, VitriolStarmetal, VitriolSoulsteel, VitriolAdamant;

  @Override
  public String getId() {
    return name();
  }
}