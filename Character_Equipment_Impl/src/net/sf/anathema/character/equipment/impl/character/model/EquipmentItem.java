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
  private final IExaltedRuleSet ruleSet;

  public EquipmentItem(IEquipmentTemplate template, IExaltedRuleSet ruleSet) {
    this.template = template;
    this.ruleSet = ruleSet;
    Collections.addAll(printedStats, template.getStats(ruleSet));
  }

  public String getDescription() {
    return template.getDescription();
  }

  public IEquipmentStats[] getStats() {
    return template.getStats(ruleSet);
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