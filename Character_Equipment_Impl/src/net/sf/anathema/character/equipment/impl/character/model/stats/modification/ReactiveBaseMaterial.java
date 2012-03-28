package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import org.apache.commons.lang3.builder.EqualsBuilder;

import static net.sf.anathema.character.equipment.MagicalMaterial.*;

public class ReactiveBaseMaterial implements BaseMaterial {

  private MagicalMaterial material;

  public ReactiveBaseMaterial(MagicalMaterial material) {
    this.material = material;
  }

  @Override
  public boolean isOrichalcumBased() {
    return material == Orichalcum || material == VitriolOrichalcum;
  }

  @Override
  public boolean isJadeBased() {
    return material == Jade || material == VitriolJade;
  }

  @Override
  public boolean isMoonsilverBased() {
    return material == Moonsilver || material == VitriolMoonsilver;
  }

  @Override
  public boolean isStarmetalBased() {
    return material == Starmetal || material == VitriolStarmetal;
  }

  @Override
  public boolean isSoulsteelBased() {
    return material == Soulsteel || material == VitriolSoulsteel;
  }

  @Override
  public boolean isAdamantBased() {
    return material == Adamant || material == VitriolAdamant;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return material.hashCode();
  }
}