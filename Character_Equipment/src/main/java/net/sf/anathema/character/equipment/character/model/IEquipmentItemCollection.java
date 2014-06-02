package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.lib.control.CollectionListener;

public interface IEquipmentItemCollection {

  IEquipmentItem addEquipmentObjectFor(String templateId, MagicalMaterial material);

  void addEquipmentObjectListener(CollectionListener listener);

  boolean canBeRemoved(IEquipmentItem item);

  String[] getAvailableTemplateIds();

  IEquipmentItem[] getEquipmentItems();

  void removeItem(IEquipmentItem item);

  IEquipmentItem[] getNaturalWeapons();
}