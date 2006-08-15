package net.sf.anathema.dummy.character.additional;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.impl.character.model.AbstractEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;

public class DemoEquipmentAdditionalModel extends AbstractEquipmentAdditionalModel {

  private final List<IArmourStats> printArmourStats = new ArrayList<IArmourStats>();
  private final List<IWeaponStats> printWeaponStats = new ArrayList<IWeaponStats>();
  private final List<IEquipmentTemplate> availableTemplates = new ArrayList<IEquipmentTemplate>();
  
  public DemoEquipmentAdditionalModel() {
    super(ExaltedRuleSet.SecondEdition);
  }

  public void addPrintArmour(IArmourStats armour) {
    this.printArmourStats.add(armour);
  }

  public void addPrintWeapon(IWeaponStats weapon) {
    this.printWeaponStats.add(weapon);
  }

  public IArmourStats[] getPrintArmours() {
    return printArmourStats.toArray(new IArmourStats[printArmourStats.size()]);
  }

  public IWeaponStats[] getPrintWeapons() {
    return printWeaponStats.toArray(new IWeaponStats[printWeaponStats.size()]);
  }

  public void addAvailableTemplates(IEquipmentTemplate template) {
    availableTemplates.add(template);
  }

  public IEquipmentTemplate[] getAvailableTemplates() {
    return availableTemplates.toArray(new IEquipmentTemplate[availableTemplates.size()]);
  }
}