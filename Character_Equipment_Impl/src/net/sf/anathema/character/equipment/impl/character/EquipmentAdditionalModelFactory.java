package net.sf.anathema.character.equipment.impl.character;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentAdditionalModel;
import net.sf.anathema.character.equipment.impl.character.model.natural.NaturalSoak;
import net.sf.anathema.character.equipment.impl.character.model.natural.NaturalWeaponTemplate;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.type.CharacterType;

public class EquipmentAdditionalModelFactory implements IAdditionalModelFactory {

  private final IEquipmentTemplateProvider equipmentTemplateProvider;

  public EquipmentAdditionalModelFactory(IEquipmentTemplateProvider equipmentTemplateProvider) {
    this.equipmentTemplateProvider = equipmentTemplateProvider;
  }

  public IAdditionalModel createModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context) {
    IEquipmentAdditionalModelTemplate template = (IEquipmentAdditionalModelTemplate) additionalTemplate;
    IBasicCharacterData basicCharacterContext = context.getBasicCharacterContext();
    CharacterType characterType = basicCharacterContext.getCharacterType();
    IArmourStats naturalArmour = new NaturalSoak(
        context.getTraitCollection().getTrait(AttributeType.Stamina),
        characterType);
    IExaltedRuleSet ruleSet = basicCharacterContext.getRuleSet();
    return new EquipmentAdditionalModel(
        getDefaultMaterial(characterType),
        naturalArmour,
        equipmentTemplateProvider,
        ruleSet,
        new NaturalWeaponTemplate(),
        template.getNaturalWeaponTemplate(characterType));
  }

  private MagicalMaterial getDefaultMaterial(CharacterType characterType) {
    MagicalMaterial defaultMaterial = MagicalMaterial.getDefault(characterType);
    if (defaultMaterial == null) {
      return MagicalMaterial.Orichalcum;
    }
    return defaultMaterial;
  }
}