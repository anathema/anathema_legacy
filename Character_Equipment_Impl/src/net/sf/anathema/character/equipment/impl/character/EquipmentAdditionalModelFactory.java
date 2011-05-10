package net.sf.anathema.character.equipment.impl.character;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentAdditionalModel;
import net.sf.anathema.character.equipment.impl.character.model.natural.NaturalSoak;
import net.sf.anathema.character.equipment.impl.character.model.natural.NaturalWeaponTemplate;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.type.ICharacterType;

public class EquipmentAdditionalModelFactory implements IAdditionalModelFactory {

  private final IEquipmentTemplateProvider equipmentTemplateProvider;

  public EquipmentAdditionalModelFactory(IEquipmentTemplateProvider equipmentTemplateProvider) {
    this.equipmentTemplateProvider = equipmentTemplateProvider;
  }

  public IAdditionalModel createModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context) {
    IEquipmentAdditionalModelTemplate template = (IEquipmentAdditionalModelTemplate) additionalTemplate;
    IBasicCharacterData basicCharacterContext = context.getBasicCharacterContext();
    ICharacterType characterType = basicCharacterContext.getCharacterType();
    IArmourStats naturalArmour = new NaturalSoak(context);
    IExaltedRuleSet ruleSet = basicCharacterContext.getRuleSet();
    return new EquipmentAdditionalModel(
        characterType,
        naturalArmour,
        equipmentTemplateProvider,
        ruleSet,
        new NaturalWeaponTemplate(),
        template.getNaturalWeaponTemplate(characterType));
  }
}