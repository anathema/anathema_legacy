package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.lib.control.collection.ICollectionListener;

public interface IEquipmentObjectCollection {

  public IEquipmentObject[] getAvailableObjects();

  public void addEquipmentObject(IEquipmentObject object);

  public void addEquipmentObjectListener(ICollectionListener<IEquipmentObject> listener);
}