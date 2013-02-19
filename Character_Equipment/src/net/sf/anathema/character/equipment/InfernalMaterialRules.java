package net.sf.anathema.character.equipment;

public class InfernalMaterialRules extends SimpleMaterialRules {
  public MagicalMaterial getDefault() {
    return MagicalMaterial.VitriolOrichalcum;
  }

  @Override
  protected boolean resonatesWithMaterial(MagicalMaterial material) {
    return MagicalMaterial.isVitriolTainted(material);
  }

  @Override
  public boolean canAttuneToMalfeanMaterials() {
    return true;
  }
}