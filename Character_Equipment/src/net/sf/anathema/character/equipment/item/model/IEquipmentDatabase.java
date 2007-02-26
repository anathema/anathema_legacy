package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IEquipmentDatabase extends IEquipmentTemplateProvider {

  public void addAvailableTemplateChangeListener(IChangeListener listener);

  public void deleteTemplate(String editTemplateId);

  public ICollectionFactory getCollectionFactory();

  public void saveTemplate(IEquipmentTemplate template);

  public void updateTemplate(String editTemplateId, IEquipmentTemplate saveTemplate);
}