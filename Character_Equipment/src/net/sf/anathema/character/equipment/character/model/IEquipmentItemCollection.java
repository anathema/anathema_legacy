package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.lib.control.collection.ICollectionListener;

public interface IEquipmentItemCollection {

  IEquipmentItem addEquipmentObjectFor(String templateId, MagicalMaterial material);

  void addEquipmentObjectListener(ICollectionListener<IEquipmentItem> listener);

  boolean canBeRemoved(IEquipmentItem item);

  String[] getAvailableTemplateIds();

  IEquipmentItem[] getEquipmentItems();

  void removeItem(IEquipmentItem item);

  IEquipmentItem[] getNaturalWeapons();
}