package net.sf.anathema.character.equipment.impl.character;

import net.sf.anathema.character.equipment.character.model.IEquipmentTemplate;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentAdditionalModel;
import net.sf.anathema.character.equipment.impl.character.model.natural.Clinch;
import net.sf.anathema.character.equipment.impl.character.model.natural.Kick;
import net.sf.anathema.character.equipment.impl.character.model.natural.NaturalSoak;
import net.sf.anathema.character.equipment.impl.character.model.natural.Punch;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.control.change.IChangeListener;

public class EquipmentAdditionalModelFactory implements IAdditionalModelFactory {

  public IAdditionalModel createModel(
      IAdditionalTemplate additionalTemplate,
      ICharacterModelContext context,
      IChangeListener[] listeners) {
    IBasicCharacterData basicCharacterContext = context.getBasicCharacterContext();
    IArmourStats naturalArmour = new NaturalSoak(
        context.getTraitCollection().getTrait(AttributeType.Stamina),
        basicCharacterContext.getCharacterType());
    IWeaponStats[] naturalWeapons = new IWeaponStats[0];
    if (basicCharacterContext.getRuleSet().getEdition() == ExaltedEdition.SecondEdition) {
      naturalWeapons = new IWeaponStats[] { new Punch(), new Kick(), new Clinch() };
    }
    return new EquipmentAdditionalModel(naturalArmour, naturalWeapons, new IEquipmentTemplate[0]);
  }
}