package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;

import static net.sf.anathema.character.equipment.MagicalMaterial.*;

public class BaseMaterial {

  private MagicalMaterial material;

  public BaseMaterial(MagicalMaterial material) {
    this.material = material;
  }

  public boolean isOrichalcumBased() {
    return material == Orichalcum || material == VitriolOrichalcum;
  }

  public boolean isJadeBased() {
    return material == Jade || material == VitriolJade;
  }

  public boolean isMoonsilverBased() {
    return material == Moonsilver || material == VitriolMoonsilver;
  }

  public boolean isStarmetalBased() {
    return material == Starmetal || material == VitriolStarmetal;
  }

  public boolean isSoulsteelBased() {
    return material == Soulsteel || material == VitriolSoulsteel;
  }

  public boolean isAdamantBased() {
    return material == Adamant || material == VitriolAdamant;
  }
}