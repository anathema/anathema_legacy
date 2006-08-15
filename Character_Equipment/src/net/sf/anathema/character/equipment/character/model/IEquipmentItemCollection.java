package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.lib.control.collection.ICollectionListener;

public interface IEquipmentItemCollection {
  
  public IEquipmentItem[] getEquipmentItems();

  public IEquipmentTemplate[] getAvailableTemplates();

  public void addEquipmentObjectFor(IEquipmentTemplate template);

  public void addEquipmentObjectListener(ICollectionListener<IEquipmentItem> listener);
}