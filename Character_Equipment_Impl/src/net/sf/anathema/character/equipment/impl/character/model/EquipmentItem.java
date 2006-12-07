package net.sf.anathema.character.equipment.impl.character.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.impl.character.model.stats.ProxyArmourStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ProxyShieldStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ProxyWeaponStats;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class EquipmentItem implements IEquipmentItem {

  private final Set<IEquipmentStats> printedStats = new HashSet<IEquipmentStats>();
  private final ChangeControl changeControl = new ChangeControl();
  private final IEquipmentTemplate template;
  private final IExaltedRuleSet ruleSet;
  private final MagicalMaterial material;

  public EquipmentItem(IEquipmentTemplate template, IExaltedRuleSet ruleSet, MagicalMaterial material) {
    if (template.getComposition() == MaterialComposition.Variable && material == null) {
      throw new MissingMaterialException("Variable material items must be created with material."); //$NON-NLS-1$
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
    if (template.getComposition() != MaterialComposition.Variable) {
      return template.getStats(ruleSet);
    }
    return ArrayUtilities.transform(
        template.getStats(ruleSet),
        IEquipmentStats.class,
        new ITransformer<IEquipmentStats, IEquipmentStats>() {
          public IEquipmentStats transform(IEquipmentStats input) {
            if (input instanceof IArmourStats) {
              return new ProxyArmourStats((IArmourStats) input, material, ruleSet);
            }
            if (input instanceof IWeaponStats) {
              return new ProxyWeaponStats((IWeaponStats) input, material, ruleSet);
            }
            return new ProxyShieldStats((IShieldStats) input, material, ruleSet);
          }
        });
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
    if (isPrintEnabled(stats) == enabled) {
      return;
    }
    if (enabled) {
      printedStats.add(stats);
    }
    else {
      printedStats.remove(stats);
    }
    changeControl.fireChangedEvent();
  }

  public void setUnprinted() {
    printedStats.clear();
    changeControl.fireChangedEvent();
  }

  public void setPrinted(String printedStatId) {
    for (IEquipmentStats stats : getStats()) {
      if (stats.getName().getId().equals(printedStatId)) {
        setPrintEnabled(stats, true);
        return;
      }
    }
  }

  public void addChangeListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  public void removeChangeListener(IChangeListener listener) {
    changeControl.removeChangeListener(listener);
  }
}