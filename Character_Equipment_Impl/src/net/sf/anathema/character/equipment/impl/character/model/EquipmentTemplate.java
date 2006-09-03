package net.sf.anathema.character.equipment.impl.character.model;

import java.util.List;
import java.util.Map;

import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class EquipmentTemplate implements IEquipmentTemplate {

  private final Map<IExaltedRuleSet, List<IEquipmentStats>> statsByRuleSet;
  private String description;
  private String name;
  private final ICollectionFactory collectionFactory;

  public EquipmentTemplate(String name, String description, ICollectionFactory collectionFactory) {
    this.name = name;
    this.description = description;
    this.collectionFactory = collectionFactory;
    this.statsByRuleSet = collectionFactory.createHashMap();
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
    if (relevantRules == null) {
      relevantRules = collectionFactory.createList();
      statsByRuleSet.put(ruleSet, relevantRules);
    }
    relevantRules.add(stats);
  }

  public String getName() {
    return name;
  }
}