package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialRules;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.main.equipment.ArtifactAttuneType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.library.trait.specialties.Specialty;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.equipment.SpecialtiesCollectionImpl;
import net.sf.anathema.lib.control.ChangeListener;

import static net.sf.anathema.character.main.equipment.ArtifactAttuneType.FullyAttuned;
import static net.sf.anathema.character.main.equipment.ArtifactAttuneType.Unattuned;
import static net.sf.anathema.character.main.equipment.ArtifactAttuneType.UnharmoniouslyAttuned;

public class EquipmentHeroEvaluatorImpl implements EquipmentHeroEvaluator {

  private Hero hero;
  private final MaterialRules materialRules;

  public EquipmentHeroEvaluatorImpl(Hero hero, MaterialRules materialRules) {
    this.hero = hero;
    this.materialRules = materialRules;
  }

  @Override
  public Specialty[] getSpecialties(TraitType trait) {
    return new SpecialtiesCollectionImpl(hero).getSpecialties(trait);
  }

  @Override
  public IEquipmentStatsOption getCharacterSpecialtyOption(String name, String type) {
    TraitType trait = AbilityType.valueOf(type);
    for (Specialty specialty : getSpecialties(trait)) {
      if (specialty.getName().equals(name)) {
        return new EquipmentSpecialtyOption(specialty, trait);
      }
    }
    return null;
  }

  @Override
  public ArtifactAttuneType[] getAttuneTypes(IEquipmentItem item) {
    MagicalMaterial material = item.getMaterial();
    switch (item.getMaterialComposition()) {
      default:
      case None:
        return new ArtifactAttuneType[0];
      case Fixed:
      case Variable:
        return materialRules.getAttunementTypes(getCharacterType(), material);
      case Compound:
      case OtherWondrous:
        return new ArtifactAttuneType[]{Unattuned, ArtifactAttuneType.FullyAttuned};
      case MalfeanMaterials:
        return createMalfeanMaterialsAttunementOptions();
    }
  }

  private ArtifactAttuneType[] createMalfeanMaterialsAttunementOptions() {
    if (materialRules.canAttuneToMalfeanMaterials(getCharacterType())) {
      return new ArtifactAttuneType[]{Unattuned, FullyAttuned};
    }
    return new ArtifactAttuneType[]{Unattuned, UnharmoniouslyAttuned};
  }

  @Override
  public void addCharacterSpecialtyListChangeListener(ChangeListener listener) {
    new SpecialtiesCollectionImpl(hero).addSpecialtyListChangeListener(listener);
  }

  private ICharacterType getCharacterType() {
    return hero.getTemplate().getTemplateType().getCharacterType();
  }
}