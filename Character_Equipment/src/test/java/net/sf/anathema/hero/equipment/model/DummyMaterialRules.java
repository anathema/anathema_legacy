package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.framework.environment.dependencies.DoNotInstantiateAutomatically;
import net.sf.anathema.hero.equipment.sheet.content.stats.ArtifactAttuneType;
import net.sf.anathema.hero.utilities.ForCharacterType;

import static net.sf.anathema.hero.equipment.model.ReflectionMaterialRulesTest.ID_TEST_TYPE;

@DoNotInstantiateAutomatically
@ForCharacterType(ID_TEST_TYPE)
public class DummyMaterialRules implements CharacterTypeMaterialRules{

  private MagicalMaterial material;

  public DummyMaterialRules(MagicalMaterial material) {
    this.material = material;
  }

  @Override
  public MagicalMaterial getDefault() {
    return material;
  }

  @Override
  public ArtifactAttuneType[] getAttunementTypes(MagicalMaterial material) {
    return new ArtifactAttuneType[0]; 
  }

  @Override
  public boolean canAttuneToMalfeanMaterials() {
    return false; 
  }
}