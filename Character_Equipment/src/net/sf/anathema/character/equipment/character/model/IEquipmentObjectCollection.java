package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.lib.control.collection.ICollectionListener;

public interface IEquipmentObjectCollection {

  public IEquipmentTemplate[] getAvailableTemplates();

  public void addEquipmentObject(IEquipmentTemplate template);

  public void addEquipmentObjectListener(ICollectionListener<IEquipmentItem> listener);
}