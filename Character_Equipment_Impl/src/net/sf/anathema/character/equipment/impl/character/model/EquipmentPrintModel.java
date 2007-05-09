package net.sf.anathema.character.equipment.impl.character.model;

import java.util.ArrayList;
import java.util.List;

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
  private final IArmourStats naturalArmour;

  public EquipmentPrintModel(IEquipmentItemCollection collection, IArmourStats naturalArmour) {
    this.collection = collection;
    this.naturalArmour = naturalArmour;
  }

  public IArmourStats[] getPrintArmours() {
    List<IArmourStats> printStats = getNaturalArmourList();
    printStats.addAll(getPrintEquipmentList(IArmourStats.class));
    return printStats.toArray(new IArmourStats[printStats.size()]);
  }

  private List<IArmourStats> getNaturalArmourList() {
    List<IArmourStats> printStats = new ArrayList<IArmourStats>();
    printStats.add(naturalArmour);
    return printStats;
  }

  public IWeaponStats[] getPrintWeapons() {
    List<IWeaponStats> printStats = getNaturalWeaponList();
    printStats.addAll(getPrintEquipmentList(IWeaponStats.class));
    return printStats.toArray(new IWeaponStats[printStats.size()]);
  }

  private List<IWeaponStats> getNaturalWeaponList() {
    List<IWeaponStats> printStats = new ArrayList<IWeaponStats>();
    for (IEquipmentItem item : collection.getNaturalWeapons()) {
      for (IEquipmentStats stats : item.getStats()) {
        if (doPrint(item, stats, IWeaponStats.class)) {
          printStats.add((IWeaponStats) stats);
        }
      }
    }
    return printStats;
  }

  public IShieldStats[] getPrintShield() {
    List<IShieldStats> printStats = getPrintEquipmentList(IShieldStats.class);
    return printStats.toArray(new IShieldStats[printStats.size()]);
  }

  @SuppressWarnings("unchecked")
  private <K extends IEquipmentStats> List<K> getPrintEquipmentList(Class<K> printedClass) {
    List<K> printStats = new ArrayList<K>();
    for (IEquipmentItem item : collection.getEquipmentItems()) {
      for (IEquipmentStats stats : item.getStats()) {
        if (doPrint(item, stats, printedClass)) {
          String itemName = item.getTemplateId();
          if (item.getStats().length > 1) {
            itemName += " - " + stats.getName(); //$NON-NLS-1$
          }
          printStats.add((K) EquipmentDecorationFactory.createRenamedPrintDecoration(stats, itemName));
        }
      }
    }
    return printStats;
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