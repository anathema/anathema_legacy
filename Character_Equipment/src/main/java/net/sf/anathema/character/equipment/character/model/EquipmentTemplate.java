package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.equipment.core.IEquipmentTemplate;
import net.sf.anathema.equipment.core.ItemCost;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.equipment.core.MaterialComposition;

import java.util.ArrayList;
import java.util.List;

public class EquipmentTemplate implements IEquipmentTemplate {

  private final List<IEquipmentStats> statsList = new ArrayList<>();
  private final String description;
  private final String name;
  private final String material;
  private final String composition;
  private final ItemCost cost;

  public EquipmentTemplate(String name, String description, MaterialComposition composition, MagicalMaterial material, ItemCost cost) {
    this.name = name;
    this.description = description;
    this.composition = composition.getId();
    if (material == null) {
      this.material = null;
    } else {
      this.material = material.getId();
    }
    this.cost = cost;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public IEquipmentStats[] getStatsList() {
    return statsList.toArray(new IEquipmentStats[statsList.size()]);
  }

  public synchronized void addStats(IEquipmentStats stats) {
    this.statsList.add(stats);
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
}