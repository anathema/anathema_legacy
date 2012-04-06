package net.sf.anathema.character.equipment.impl.character.model;

import net.sf.anathema.character.equipment.IWeaponModifiers;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponModifiers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EquipmentCollection implements Iterable<IEquipmentItem>, ModifierFactory {
  private final List<IEquipmentItem> equipmentItems = new ArrayList<IEquipmentItem>();

  public IEquipmentItem[] asArray() {
    return equipmentItems.toArray(new IEquipmentItem[equipmentItems.size()]);
  }

  public void add(IEquipmentItem item) {
    equipmentItems.add(item);
  }

  public void remove(IEquipmentItem item) {
    equipmentItems.remove(item);
  }

  @Override
  public Iterator<IEquipmentItem> iterator() {
    return new ArrayList<IEquipmentItem>(equipmentItems).iterator();
  }

  @Override
  public IWeaponModifiers createModifiers() {
    return new WeaponModifiers(equipmentItems);
  }
}