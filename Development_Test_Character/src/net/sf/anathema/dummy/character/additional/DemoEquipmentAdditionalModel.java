package net.sf.anathema.dummy.character.additional;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentTemplate;
import net.sf.anathema.character.equipment.impl.character.model.AbstractEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentItem;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.collection.ICollectionListener;

public class DemoEquipmentAdditionalModel extends AbstractEquipmentAdditionalModel {

  private final List<IArmourStats> printArmourStats = new ArrayList<IArmourStats>();
  private final List<IWeaponStats> printWeaponStats = new ArrayList<IWeaponStats>();
  private final List<IEquipmentTemplate> availableTemplates = new ArrayList<IEquipmentTemplate>();
  private final List<IEquipmentItem> equipmentObjects = new ArrayList<IEquipmentItem>();
  private final GenericControl<ICollectionListener<IEquipmentItem>> equipmentObjectControl = new GenericControl<ICollectionListener<IEquipmentItem>>();

  public void addPrintArmour(IArmourStats armour) {
    this.printArmourStats.add(armour);
  }

  public void addPrintWeapon(IWeaponStats weapon) {
    this.printWeaponStats.add(weapon);
  }

  public IArmourStats[] getPrintArmours() {
    return printArmourStats.toArray(new IArmourStats[printArmourStats.size()]);
  }

  public IWeaponStats[] getPrintWeapons() {
    return printWeaponStats.toArray(new IWeaponStats[printWeaponStats.size()]);
  }

  public void addAvailableTemplates(IEquipmentTemplate template) {
    availableTemplates.add(template);
  }

  public void addEquipmentObject(final IEquipmentTemplate template) {
    final IEquipmentItem item = new EquipmentItem(template);
    equipmentObjects.add(item);
    equipmentObjectControl.forAllDo(new IClosure<ICollectionListener<IEquipmentItem>>() {
      public void execute(ICollectionListener<IEquipmentItem> input) {
        input.itemAdded(item);
      }
    });
  }

  public IEquipmentTemplate[] getAvailableTemplates() {
    return availableTemplates.toArray(new IEquipmentTemplate[availableTemplates.size()]);
  }

  public void addEquipmentObjectListener(ICollectionListener<IEquipmentItem> listener) {
    equipmentObjectControl.addListener(listener);
  }
}