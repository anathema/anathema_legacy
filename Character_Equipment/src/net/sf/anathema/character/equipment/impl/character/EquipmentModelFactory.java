package net.sf.anathema.character.equipment.impl.character;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.MaterialRules;
import net.sf.anathema.character.equipment.character.EquipmentHeroEvaluatorImpl;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentModelImpl;
import net.sf.anathema.character.equipment.impl.character.model.natural.DefaultNaturalSoak;
import net.sf.anathema.character.equipment.impl.character.model.natural.NaturalWeaponTemplate;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.hero.model.Hero;

public class EquipmentModelFactory implements IAdditionalModelFactory {

  private final IEquipmentTemplateProvider equipmentTemplateProvider;
  private final MaterialRules materialRules;

  public EquipmentModelFactory(IEquipmentTemplateProvider equipmentTemplateProvider, MaterialRules materialRules) {
    this.equipmentTemplateProvider = equipmentTemplateProvider;
    this.materialRules = materialRules;
  }

  @Override
  public IAdditionalModel createModel(IAdditionalTemplate additionalTemplate, Hero hero) {
    IEquipmentAdditionalModelTemplate template = (IEquipmentAdditionalModelTemplate) additionalTemplate;
    ICharacterType characterType = hero.getTemplate().getTemplateType().getCharacterType();
    Trait stamina = TraitModelFetcher.fetch(hero).getTrait(AttributeType.Stamina);
    IArmourStats naturalArmour = new DefaultNaturalSoak(stamina, characterType);
    EquipmentHeroEvaluatorImpl dataProvider = new EquipmentHeroEvaluatorImpl(hero, materialRules);
    return new EquipmentModelImpl(hero, characterType, naturalArmour, equipmentTemplateProvider, dataProvider,
            materialRules, new NaturalWeaponTemplate(), template.getNaturalWeaponTemplate(characterType));
  }
}
