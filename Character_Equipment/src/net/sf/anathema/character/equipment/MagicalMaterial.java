package net.sf.anathema.character.equipment;

import net.sf.anathema.lib.util.Identifier;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public enum MagicalMaterial implements Identifier {
  Orichalcum, Jade, Moonsilver, Starmetal, Soulsteel, Adamant,
  VitriolOrichalcum, VitriolJade, VitriolMoonsilver, VitriolStarmetal, VitriolSoulsteel, VitriolAdamant;

  @Override
  public String getId() {
    return name();
  }

  public static boolean isVitriolTainted(MagicalMaterial material) {
    List<MagicalMaterial> vitriolTaintedMaterials = newArrayList(VitriolAdamant, VitriolJade, VitriolMoonsilver, VitriolOrichalcum, VitriolSoulsteel, VitriolStarmetal);
    return vitriolTaintedMaterials.contains(material);
  }
}