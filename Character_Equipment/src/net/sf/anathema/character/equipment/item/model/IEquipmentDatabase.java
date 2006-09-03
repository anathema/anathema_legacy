package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.exception.PersistenceException;

public interface IEquipmentDatabase {

  public void addAvailableTemplateChangeListener(IChangeListener listener);

  public void deleteTemplate(String editTemplateId);

  public String[] getAllAvailableTemplateIds();

  public ICollectionFactory getCollectionFactory();

  public IEquipmentTemplate loadTemplate(String templateId);

  public void saveTemplate(IEquipmentTemplate template) throws PersistenceException;

  public void updateTemplate(String editTemplateId, IEquipmentTemplate saveTemplate);
}