package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.equipment.core.MagicalMaterial;
import org.apache.commons.lang3.builder.EqualsBuilder;

import static net.sf.anathema.equipment.core.MagicalMaterial.Adamant;
import static net.sf.anathema.equipment.core.MagicalMaterial.Jade;
import static net.sf.anathema.equipment.core.MagicalMaterial.Moonsilver;
import static net.sf.anathema.equipment.core.MagicalMaterial.Orichalcum;
import static net.sf.anathema.equipment.core.MagicalMaterial.Soulsteel;
import static net.sf.anathema.equipment.core.MagicalMaterial.Starmetal;
import static net.sf.anathema.equipment.core.MagicalMaterial.VitriolAdamant;
import static net.sf.anathema.equipment.core.MagicalMaterial.VitriolJade;
import static net.sf.anathema.equipment.core.MagicalMaterial.VitriolMoonsilver;
import static net.sf.anathema.equipment.core.MagicalMaterial.VitriolOrichalcum;
import static net.sf.anathema.equipment.core.MagicalMaterial.VitriolSoulsteel;
import static net.sf.anathema.equipment.core.MagicalMaterial.VitriolStarmetal;

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

  @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return material.hashCode();
  }
}