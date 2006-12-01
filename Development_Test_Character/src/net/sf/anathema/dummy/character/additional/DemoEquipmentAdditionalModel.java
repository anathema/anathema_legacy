package net.sf.anathema.dummy.character.additional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.impl.character.model.AbstractEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.lib.control.change.IChangeListener;

public class DemoEquipmentAdditionalModel extends AbstractEquipmentAdditionalModel {
  private final List<IArmourStats> printArmourStats = new ArrayList<IArmourStats>();
  private final List<IWeaponStats> printWeaponStats = new ArrayList<IWeaponStats>();
  private final Map<String, IEquipmentTemplate> availableTemplates = new HashMap<String, IEquipmentTemplate>();

  public DemoEquipmentAdditionalModel() {
    super(ExaltedRuleSet.SecondEdition, null);
  }

  public IEquipmentItem[] getNaturalWeapons() {
    return new IEquipmentItem[0];
  }

  public void addPrintArmour(IArmourStats armour) {
    this.printArmourStats.add(armour);
  }

  public void addPrintWeapon(IWeaponStats weapon) {
    this.printWeaponStats.add(weapon);
  }

  public boolean canBeRemoved(IEquipmentItem item) {
    return true;
  }

  public IArmourStats[] getPrintArmours() {
    return printArmourStats.toArray(new IArmourStats[printArmourStats.size()]);
  }

  public IWeaponStats[] getPrintWeapons() {
    return printWeaponStats.toArray(new IWeaponStats[printWeaponStats.size()]);
  }

  public IShieldStats[] getPrintShield() {
    return new IShieldStats[0];
  }

  public void addAvailableTemplates(IEquipmentTemplate template) {
    availableTemplates.put(template.getName(), template);
  }

  @Override
  protected IEquipmentTemplate loadEquipmentTemplate(String templateId) {
    return availableTemplates.get(templateId);
  }

  public String[] getAvailableTemplateIds() {
    Set<String> templateIds = availableTemplates.keySet();
    return templateIds.toArray(new String[templateIds.size()]);
  }

  @Override
  protected IEquipmentItem getSpecialManagedItem(String templateId) {
    return null;
  }

  public MagicalMaterial getDefaultMaterial() {
    return MagicalMaterial.Soulsteel;
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    // nothing to do
  }
}