package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.exception.PersistenceException;

public interface IEquipmentDatabase extends IEquipmentTemplateProvider {

  public void addAvailableTemplateChangeListener(IChangeListener listener);

  public void deleteTemplate(String editTemplateId);

  public ICollectionFactory getCollectionFactory();

  public void saveTemplate(IEquipmentTemplate template) throws PersistenceException;

  public void updateTemplate(String editTemplateId, IEquipmentTemplate saveTemplate);
}