package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.stats.modification.WeaponModifiers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class EquipmentCollection implements Iterable<IEquipmentItem>, ModifierFactory {
  private final List<IEquipmentItem> equipmentItems = new ArrayList<>();

  public void add(IEquipmentItem item) {
    equipmentItems.add(item);
  }

  public void remove(IEquipmentItem item) {
    equipmentItems.remove(item);
  }

  @Override
  public Iterator<IEquipmentItem> iterator() {
    return new ArrayList<>(equipmentItems).iterator();
  }

  @Override
  public IWeaponModifiers createModifiers() {
    return new WeaponModifiers(equipmentItems);
  }

  public Collection<IEquipmentItem> asList() {
    return equipmentItems;
  }

  public boolean contains(IEquipmentItem item) {
    return equipmentItems.contains(item);
  }
}