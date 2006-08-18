package net.sf.anathema.character.equipment.impl.character.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class EquipmentTemplate implements IEquipmentTemplate {

  private final Map<IExaltedRuleSet, List<IEquipmentStats>> statsByRuleSet = new HashMap<IExaltedRuleSet, List<IEquipmentStats>>();
  private String description;
  private String name;

  public EquipmentTemplate(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public IEquipmentStats[] getStats(IExaltedRuleSet ruleSet) {
    List<IEquipmentStats> relevantStats = statsByRuleSet.get(ruleSet);
    if (relevantStats == null) {
      return new IEquipmentStats[0];
    }
    return relevantStats.toArray(new IEquipmentStats[relevantStats.size()]);
  }
  
  public synchronized void addStats(IExaltedRuleSet ruleSet, IEquipmentStats stats) {
    List<IEquipmentStats> relevantRules = statsByRuleSet.get(stats);
    // TODO Hier muss bei Umstellung auf Datenbank eine richtige Liste erzeugt werden
    if (relevantRules == null) {
      relevantRules = new ArrayList<IEquipmentStats>();
      statsByRuleSet.put(ruleSet, relevantRules);
    }
    relevantRules.add(stats);
  }

  public String getName() {
    return name;
  }
}