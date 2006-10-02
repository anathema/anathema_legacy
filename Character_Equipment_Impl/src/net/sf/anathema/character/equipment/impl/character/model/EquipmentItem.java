package net.sf.anathema.character.equipment.impl.character.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class EquipmentItem implements IEquipmentItem {

  private List<IEquipmentStats> printedStats = new ArrayList<IEquipmentStats>();
  private final IEquipmentTemplate template;
  private final IExaltedRuleSet ruleSet;
  private final MagicalMaterial material;

  public EquipmentItem(IEquipmentTemplate template, IExaltedRuleSet ruleSet, MagicalMaterial material) {
    if (template.getComposition() == MaterialComposition.Variable && material == null) {
      throw new MissingMaterialException("Variable material items must be created with material.");
    }
    this.template = template;
    this.ruleSet = ruleSet;
    this.material = material != null ? material : template.getMaterial();
    Collections.addAll(printedStats, template.getStats(ruleSet));
  }

  public String getDescription() {
    return template.getDescription();
  }

  public IEquipmentStats[] getStats() {
    return template.getStats(ruleSet);
  }

  public String getTemplateId() {
    return template.getName();
  }

  public MagicalMaterial getMaterial() {
    return material;
  }
  
  public MaterialComposition getMaterialComposition() {
    return template.getComposition();
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

  public void setUnprinted() {
    printedStats.clear();
  }

  public void setPrinted(String printedStatId) {
    for (IEquipmentStats stats : getStats()) {
      if (stats.getName().getId().equals(printedStatId)) {
        printedStats.add(stats);
      }
    }
  }
}