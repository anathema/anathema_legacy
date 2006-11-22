package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.lib.control.collection.ICollectionListener;

public interface IEquipmentItemCollection {

  public IEquipmentItem addEquipmentObjectFor(String templateId, MagicalMaterial material);

  public void addEquipmentObjectListener(ICollectionListener<IEquipmentItem> listener);

  public boolean canBeRemoved(IEquipmentItem item);

  public String[] getAvailableTemplateIds();

  public IEquipmentItem[] getEquipmentItems();

  public void removeItem(IEquipmentItem item);

  public IEquipmentItem[] getNaturalWeapons();
}