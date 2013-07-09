package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.character.main.equipment.ArtifactAttuneType;
import net.sf.anathema.equipment.core.MagicalMaterial;

import static net.sf.anathema.character.main.equipment.ArtifactAttuneType.FullyAttuned;
import static net.sf.anathema.character.main.equipment.ArtifactAttuneType.PartiallyAttuned;
import static net.sf.anathema.character.main.equipment.ArtifactAttuneType.Unattuned;
import static net.sf.anathema.character.main.equipment.ArtifactAttuneType.UnharmoniouslyAttuned;

public abstract class SimpleMaterialRules implements CharacterTypeMaterialRules {

  public ArtifactAttuneType[] getAttunementTypes(MagicalMaterial material) {
    return getDefaultAttunementOptions(resonatesWithMaterial(material));
  }

  @Override
  public boolean canAttuneToMalfeanMaterials() {
    return false;
  }

  protected boolean resonatesWithMaterial(MagicalMaterial material) {
    return material == getDefault();
  }

  protected ArtifactAttuneType[] getNullAttunementTypes() {
    return new ArtifactAttuneType[]{Unattuned};
  }

  protected ArtifactAttuneType[] getDefaultAttunementOptions(boolean resonatesWithMaterial) {
    if (resonatesWithMaterial) {
      return new ArtifactAttuneType[]{Unattuned, FullyAttuned};
    }
    return new ArtifactAttuneType[]{Unattuned, PartiallyAttuned, UnharmoniouslyAttuned};
  }
}