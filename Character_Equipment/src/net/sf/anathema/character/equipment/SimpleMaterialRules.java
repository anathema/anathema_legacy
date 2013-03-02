package net.sf.anathema.character.equipment;

import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;

import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.FullyAttuned;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.PartiallyAttuned;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.Unattuned;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.UnharmoniouslyAttuned;

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