package net.sf.anathema.character.equipment.impl.character.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

import com.db4o.query.Predicate;

public class EquipmentAdditionalModel extends AbstractEquipmentAdditionalModel {
  private final IArmourStats naturalArmour;
  private final IEquipmentTemplateProvider equipmentTemplateProvider;
  private IEquipmentItem naturalWeaponsItem;
  private final MagicalMaterial defaultMaterial;

  public EquipmentAdditionalModel(
      MagicalMaterial defaultMaterial,
      IArmourStats naturalArmour,
      IEquipmentTemplate naturalWeapons,
      IEquipmentTemplateProvider equipmentTemplateProvider,
      IExaltedRuleSet ruleSet) {
    super(ruleSet);
    this.defaultMaterial = defaultMaterial;
    this.naturalArmour = naturalArmour;
    this.equipmentTemplateProvider = equipmentTemplateProvider;
    if (naturalWeapons != null) {
      naturalWeaponsItem = addEquipmentObjectFor(naturalWeapons, null);
    }
  }

  public boolean canBeRemoved(IEquipmentItem item) {
    return item != naturalWeaponsItem;
  }

  public IArmourStats[] getPrintArmours() {
    List<IArmourStats> printStats = new ArrayList<IArmourStats>();
    printStats.add(naturalArmour);
    fillPrintEquipmentList(printStats, IArmourStats.class);
    return printStats.toArray(new IArmourStats[printStats.size()]);
  }

  public IWeaponStats[] getPrintWeapons() {
    List<IWeaponStats> printStats = new ArrayList<IWeaponStats>();
    fillPrintEquipmentList(printStats, IWeaponStats.class);
    return printStats.toArray(new IWeaponStats[printStats.size()]);
  }

  public IShieldStats[] getPrintShield() {
    List<IShieldStats> printStats = new ArrayList<IShieldStats>();
    fillPrintEquipmentList(printStats, IShieldStats.class);
    return printStats.toArray(new IShieldStats[printStats.size()]);
  }

  @SuppressWarnings("unchecked")
  private <K extends IEquipmentStats> void fillPrintEquipmentList(List<K> printStats, Class<K> printedClass) {
    for (IEquipmentItem item : getEquipmentItems()) {
      if (item == naturalWeaponsItem) {
        for (IEquipmentStats stats : naturalWeaponsItem.getStats()) {
          if (doPrint(naturalWeaponsItem, stats, printedClass)) {
            printStats.add((K) stats);
          }
        }
      }
      else {
        IEquipmentStats[] statsArray = item.getStats();
        for (IEquipmentStats stats : statsArray) {
          if (doPrint(item, stats, printedClass)) {
            String itemName = item.getTemplateId();
            if (statsArray.length > 1) {
              itemName += " - " + stats.getName(); //$NON-NLS-1$
            }
            printStats.add((K) EquipmentCloneUtilities.getRenamedPrintClone(stats, itemName));
          }
        }
      }
    }
  }

  private <K> boolean doPrint(IEquipmentItem item, IEquipmentStats stats, Class<K> printedClass) {
    return (printedClass.isInstance(stats) && item.isPrintEnabled(stats));
  }

  public String[] getAvailableTemplateIds() {
    final Set<String> idSet = new HashSet<String>();
    equipmentTemplateProvider.queryContainer(new Predicate<IEquipmentTemplate>() {
      @Override
      public boolean match(IEquipmentTemplate candidate) {
        if (candidate.getStats(getRuleSet()).length > 0) {
          idSet.add(candidate.getName());
        }
        else {
          for (IExaltedRuleSet rules : ExaltedRuleSet.values()) {
            if (candidate.getStats(rules).length > 0) {
              return false;
            }
          }
          idSet.add(candidate.getName());
        }
        return false;
      }
    });
    return idSet.toArray(new String[idSet.size()]);
  }

  @Override
  protected IEquipmentTemplate loadEquipmentTemplate(String templateId) {
    return equipmentTemplateProvider.loadTemplate(templateId);
  }

  @Override
  protected IEquipmentItem getSpecialManagedItem(String templateId) {
    if (templateId.equals(naturalWeaponsItem.getTemplateId())) {
      return naturalWeaponsItem;
    }
    return null;
  }

  public MagicalMaterial getDefaultMaterial() {
    return defaultMaterial;
  }
}