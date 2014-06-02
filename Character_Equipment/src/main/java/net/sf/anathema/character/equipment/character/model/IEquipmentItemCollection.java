package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.lib.control.ICollectionListener;

public interface IEquipmentItemCollection {

  IEquipmentItem addEquipmentObjectFor(String templateId, MagicalMaterial material);

  void addEquipmentObjectListener(ICollectionListener listener);

  boolean canBeRemoved(IEquipmentItem item);

  String[] getAvailableTemplateIds();

  IEquipmentItem[] getEquipmentItems();

  void removeItem(IEquipmentItem item);

  IEquipmentItem[] getNaturalWeapons();
}