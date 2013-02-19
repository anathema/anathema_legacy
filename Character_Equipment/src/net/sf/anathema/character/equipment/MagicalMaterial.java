package net.sf.anathema.character.equipment;

import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.generic.type.ICharacterTypeVisitor;
import net.sf.anathema.lib.util.Identified;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.PartiallyAttuned;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.Unattuned;

public enum MagicalMaterial implements Identified {
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