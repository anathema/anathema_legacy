package net.sf.anathema.character.equipment.impl.character.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class EquipmentAdditionalModel extends AbstractEquipmentAdditionalModel {
  private final IArmourStats naturalArmour;
  private final IEquipmentTemplateProvider equipmentTemplateProvider;

  public EquipmentAdditionalModel(
      IArmourStats naturalArmour,
      IEquipmentTemplate naturalWeapons,
      IEquipmentTemplateProvider equipmentTemplateProvider,
      IExaltedRuleSet ruleSet) {
    super(ruleSet);
    this.naturalArmour = naturalArmour;
    this.equipmentTemplateProvider = equipmentTemplateProvider;
    if (naturalWeapons != null) {
      addEquipmentObjectFor(naturalWeapons);
    }
  }

  public IArmourStats[] getPrintArmours() {
    List<IArmourStats> printStats = new ArrayList<IArmourStats>();
    printStats.add(naturalArmour);
    for (IEquipmentItem item : getEquipmentItems()) {
      IEquipmentStats[] statsArray = item.getStats();
      if (statsArray.length == 1) {
        //TODO: Clone, change name to template name, then add
      }
      for (IEquipmentStats stats : statsArray) {
        if (stats instanceof IArmourStats && item.isPrintEnabled(stats)) {
          printStats.add((IArmourStats) stats);
        }
      }
    }
    return printStats.toArray(new IArmourStats[printStats.size()]);
  }

  public IWeaponStats[] getPrintWeapons() {
    List<IWeaponStats> printStats = new ArrayList<IWeaponStats>();
    for (IEquipmentItem item : getEquipmentItems()) {
      IEquipmentStats[] statsArray = item.getStats();
      if (statsArray.length == 1) {
        //TODO: Clone, change name to template name, then add
      }
      for (IEquipmentStats stats : statsArray) {
        if (stats instanceof IWeaponStats && item.isPrintEnabled(stats)) {
          printStats.add((IWeaponStats) stats);
        }
      }
    }
    return printStats.toArray(new IWeaponStats[printStats.size()]);
  }

  public String[] getAvailableTemplateIds() {
    return equipmentTemplateProvider.getAllAvailableTemplateIds();
  }

  @Override
  protected IEquipmentTemplate loadEquipmentTemplate(String templateId) {
    return equipmentTemplateProvider.loadTemplate(templateId);
  }
}