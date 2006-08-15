package net.sf.anathema.character.equipment.impl.character.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class EquipmentItem implements IEquipmentItem {

  private List<IEquipmentStats> printedStats = new ArrayList<IEquipmentStats>();
  private final IEquipmentTemplate template;

  public EquipmentItem(IEquipmentTemplate template, IExaltedRuleSet ruleSet) {
    this.template = template;
    Collections.addAll(printedStats, template.getEquipmentStats());
  }

  public String getDescription() {
    return template.getDescription();
  }

  public IEquipmentStats[] getEquipments() {
    return template.getEquipmentStats();
  }

  public String getName() {
    return template.getName();
  }
  
  public boolean isPrintEnabled(IEquipmentStats stats) {
    return printedStats.contains(stats);
  }

  public void setPrintEnabled(IEquipmentStats stats, boolean enabled) {
    if (enabled) {
      printedStats.add(stats);
    }
    else {
      printedStats.remove(stats);
    }
  }
}