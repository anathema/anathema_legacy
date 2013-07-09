package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.equipment.core.IEquipmentTemplate;
import net.sf.anathema.lib.control.ChangeListener;

public interface IEquipmentDatabase extends IEquipmentTemplateProvider {

  void addAvailableTemplateChangeListener(ChangeListener listener);

  void deleteTemplate(String editTemplateId);

  void saveTemplate(IEquipmentTemplate template);

  void updateTemplate(String editTemplateId, IEquipmentTemplate saveTemplate);
}