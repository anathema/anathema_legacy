package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.lib.control.collection.ICollectionListener;

public interface IEquipmentItemCollection {

  public IEquipmentItem[] getEquipmentItems();

  public String[] getAvailableTemplateIds();

  public void addEquipmentObjectFor(String templateId);

  public void addEquipmentObjectListener(ICollectionListener<IEquipmentItem> listener);

  public void removeItem(IEquipmentItem selectedObject);
}