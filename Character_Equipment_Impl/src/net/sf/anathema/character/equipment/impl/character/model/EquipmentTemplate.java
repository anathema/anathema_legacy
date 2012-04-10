package net.sf.anathema.character.equipment.impl.character.model;

import net.sf.anathema.character.equipment.ItemCost;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.impl.persistence.SecondEdition;

import java.util.List;
import java.util.Map;

public class EquipmentTemplate implements IEquipmentTemplate {

  private final Map<String, List<IEquipmentStats>> statsByRuleSet;
  private final String description;
  private final String name;
  private final ICollectionFactory collectionFactory;
  private final String material;
  private final String composition;
  private final ItemCost cost;

  public EquipmentTemplate(
      String name,
      String description,
      MaterialComposition composition,
      MagicalMaterial material,
      ICollectionFactory collectionFactory,
      ItemCost cost) {
    this.name = name;
    this.description = description;
    this.composition = composition.getId();
    if (material == null) {
      this.material = null;
    }
    else {
      this.material = material.getId();
    }
    this.collectionFactory = collectionFactory;
    this.statsByRuleSet = collectionFactory.createHashMap();
    this.cost = cost;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public IEquipmentStats[] getStats() {
    List<IEquipmentStats> relevantStats = statsByRuleSet.get(new SecondEdition().getId());
    if (relevantStats == null) {
      return new IEquipmentStats[0];
    }
    return relevantStats.toArray(new IEquipmentStats[relevantStats.size()]);
  }

  public synchronized void addStats(IEquipmentStats stats) {
    List<IEquipmentStats> statList = statsByRuleSet.get(new SecondEdition().getId());
    if (statList == null) {
      statList = collectionFactory.createList();
      statsByRuleSet.put(new SecondEdition().getId(), statList);
    }
    statList.add(stats);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public MagicalMaterial getMaterial() {
    if (material == null) {
      return null;
    }
    return MagicalMaterial.valueOf(material);
  }

  @Override
  public MaterialComposition getComposition() {
    return MaterialComposition.valueOf(composition);
  }
  
  @Override
  public ItemCost getCost() {
	return cost;
  }

  public boolean hasStats() {
    boolean hasStats = false;
    for (String key : statsByRuleSet.keySet()) {
      hasStats = !statsByRuleSet.get(key).isEmpty();
    }
    return hasStats;
  }

  public void removeStats(String ruleset) {
    statsByRuleSet.remove(ruleset);
  }

  public void removeStats(IExaltedEdition edition, IEquipmentStats stat) {
    statsByRuleSet.get(edition.getId()).remove(stat);
  }
}