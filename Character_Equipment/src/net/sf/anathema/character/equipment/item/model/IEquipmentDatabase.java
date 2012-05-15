package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.lib.control.IChangeListener;

public interface IEquipmentDatabase extends IEquipmentTemplateProvider {

  void addAvailableTemplateChangeListener(IChangeListener listener);

  void deleteTemplate(String editTemplateId);

  ICollectionFactory getCollectionFactory();

  void saveTemplate(IEquipmentTemplate template);

  void updateTemplate(String editTemplateId, IEquipmentTemplate saveTemplate);
}