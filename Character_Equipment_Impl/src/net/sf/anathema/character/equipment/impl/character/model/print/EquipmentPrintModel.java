package net.sf.anathema.character.equipment.impl.character.model.print;

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
import net.sf.anathema.lib.resources.IResources;

public class EquipmentPrintModel implements IEquipmentPrintModel {

  private final IEquipmentItemCollection collection;
  private final IArmourStats naturalArmour;

  public EquipmentPrintModel(IEquipmentItemCollection collection, IArmourStats naturalArmour) {
    this.collection = collection;
    this.naturalArmour = naturalArmour;
  }

  public IArmourStats[] getPrintArmours() {
    List<IArmourStats> printStats = getNaturalArmourList();
    printStats.addAll(getPrintEquipmentList(IArmourStats.class, new ArmourStatsDecorationFactory()));
    return printStats.toArray(new IArmourStats[printStats.size()]);
  }

  private List<IArmourStats> getNaturalArmourList() {
    List<IArmourStats> printStats = new ArrayList<IArmourStats>();
    printStats.add(naturalArmour);
    return printStats;
  }

  public IWeaponStats[] getPrintWeapons(IResources resources) {
    List<IWeaponStats> printStats = getNaturalWeaponList();
    printStats.addAll(getPrintEquipmentList(IWeaponStats.class, new WeaponStatsDecorationFactory(resources)));
    return printStats.toArray(new IWeaponStats[printStats.size()]);
  }

  private List<IWeaponStats> getNaturalWeaponList() {
    List<IWeaponStats> printStats = new ArrayList<IWeaponStats>();
    for (IEquipmentItem item : collection.getNaturalWeapons()) {
      printStats.addAll(getPrintedStats(item, IWeaponStats.class));
    }
    return printStats;
  }

  public IShieldStats[] getPrintShield() {
    List<IShieldStats> printStats = getPrintEquipmentList(IShieldStats.class, new ShieldStatsDecorationFactory());
    return printStats.toArray(new IShieldStats[printStats.size()]);
  }

  private <K extends IEquipmentStats> List<K> getPrintEquipmentList(
      Class<K> printedClass,
      IEquipmentStatsDecorationFactory<K> factory) {
    List<K> printStats = new ArrayList<K>();
    for (IEquipmentItem item : collection.getEquipmentItems()) {
      for (K stats : getPrintedStats(item, printedClass)) {
        printStats.add(factory.createRenamedPrintDecoration(item, stats));
      }
    }
    return printStats;
  }

  @SuppressWarnings("unchecked")
  private <K> List<K> getPrintedStats(IEquipmentItem item, Class<K> printedClass) {
    List<K> printedStats = new ArrayList<K>();
    for (IEquipmentStats stats : item.getStats()) {
      if (doPrint(item, stats, printedClass)) {
        printedStats.add((K) stats);
      }
    }
    return printedStats;
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