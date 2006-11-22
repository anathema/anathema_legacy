package net.sf.anathema.character.equipment.impl.character.model;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentItemCollection;
import net.sf.anathema.character.equipment.character.model.IEquipmentPrintModel;
import net.sf.anathema.character.equipment.impl.character.model.natural.TotalArmour;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;

public class EquipmentPrintModel implements IEquipmentPrintModel {

  private final IEquipmentItemCollection collection;

  public EquipmentPrintModel(IEquipmentItemCollection collection) {
    this.collection = collection;
  }

  public IArmourStats[] getPrintArmours() {
    List<IArmourStats> printStats = new ArrayList<IArmourStats>();
    printStats.add(getNaturalArmour());
    fillPrintEquipmentList(printStats, IArmourStats.class);
    return printStats.toArray(new IArmourStats[printStats.size()]);
  }

  protected IArmourStats getNaturalArmour() {
    return collection.getNaturalArmour();
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
    IEquipmentItem[] naturalWeapons = collection.getNaturalWeapons();
    for (IEquipmentItem item : collection.getEquipmentItems()) {
      if (ArrayUtilities.contains(naturalWeapons, item)) {
        for (IEquipmentStats stats : item.getStats()) {
          if (doPrint(item, stats, printedClass)) {
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
            printStats.add((K) EquipmentDEcorationUtilities.getRenamedPrintDecoration(stats, itemName));
          }
        }
      }
    }
  }

  private <K> boolean doPrint(IEquipmentItem item, IEquipmentStats stats, Class<K> printedClass) {
    return printedClass.isInstance(stats) && item.isPrintEnabled(stats);
  }

  public final IArmourStats getTotalPrintArmour(int lineCount) {
    TotalArmour armour = new TotalArmour();
    IArmourStats[] printArmours = getPrintArmours();
    for (int index = 0; index < Math.min(lineCount, printArmours.length); index++) {
      armour.addArmour(printArmours[index]);
    }
    return armour;
  }
}