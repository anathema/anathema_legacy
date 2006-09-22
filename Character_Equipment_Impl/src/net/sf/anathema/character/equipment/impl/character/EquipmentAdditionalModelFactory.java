package net.sf.anathema.character.equipment.impl.character;

import net.sf.anathema.character.equipment.impl.character.model.EquipmentAdditionalModel;
import net.sf.anathema.character.equipment.impl.character.model.natural.NaturalSoak;
import net.sf.anathema.character.equipment.impl.character.model.natural.NaturalWeaponTemplate;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.traits.types.AttributeType;

public class EquipmentAdditionalModelFactory implements IAdditionalModelFactory {

  private final IEquipmentTemplateProvider equipmentTemplateProvider;

  public EquipmentAdditionalModelFactory(IEquipmentTemplateProvider equipmentTemplateProvider) {
    this.equipmentTemplateProvider = equipmentTemplateProvider;
  }

  public IAdditionalModel createModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context) {
    IBasicCharacterData basicCharacterContext = context.getBasicCharacterContext();
    IArmourStats naturalArmour = new NaturalSoak(
        context.getTraitCollection().getTrait(AttributeType.Stamina),
        basicCharacterContext.getCharacterType());
    IEquipmentTemplate naturalWeapons = null;
    if (basicCharacterContext.getRuleSet().getEdition() == ExaltedEdition.SecondEdition) {
      naturalWeapons = new NaturalWeaponTemplate();
    }
    IExaltedRuleSet ruleSet = context.getBasicCharacterContext().getRuleSet();
    return new EquipmentAdditionalModel(naturalArmour, naturalWeapons, equipmentTemplateProvider, ruleSet);
  }
}