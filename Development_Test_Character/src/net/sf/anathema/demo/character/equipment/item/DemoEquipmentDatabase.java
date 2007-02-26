package net.sf.anathema.demo.character.equipment.item;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.anathema.character.equipment.impl.character.model.natural.NaturalWeaponTemplate;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.demo.character.equipment.DemoCollectionFactory;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

import com.db4o.query.Predicate;

public class DemoEquipmentDatabase implements IEquipmentDatabase {

  private Map<String, IEquipmentTemplate> templatesById = new HashMap<String, IEquipmentTemplate>();
  private ICollectionFactory collectionFactory = new DemoCollectionFactory();
  private final ChangeControl availableTemplatesChangeControl = new ChangeControl();

  public DemoEquipmentDatabase() {
    addTemplate(new NaturalWeaponTemplate());
    addTemplate(new NaturalWeaponTemplate() {
      @Override
      public String getName() {
        return "Second Template"; //$NON-NLS-1$
      }
    });
  }

  private void addTemplate(IEquipmentTemplate template) {
    templatesById.put(template.getName(), template);
    availableTemplatesChangeControl.fireChangedEvent();
  }

  public String[] getAllAvailableTemplateIds() {
    Set<String> idSet = templatesById.keySet();
    return idSet.toArray(new String[idSet.size()]);
  }

  public void queryContainer(Predicate<IEquipmentTemplate> predicate) {
    // nothing to do
  }

  public IEquipmentTemplate loadTemplate(String templateId) {
    return templatesById.get(templateId);
  }

  public ICollectionFactory getCollectionFactory() {
    return collectionFactory;
  }

  public void saveTemplate(IEquipmentTemplate template) {
    addTemplate(template);
  }

  public void addAvailableTemplateChangeListener(IChangeListener listener) {
    availableTemplatesChangeControl.addChangeListener(listener);
  }

  public void deleteTemplate(String editTemplateId) {
    templatesById.remove(editTemplateId);
    availableTemplatesChangeControl.fireChangedEvent();
  }

  public void updateTemplate(String editTemplateId, IEquipmentTemplate saveTemplate) {
    templatesById.remove(editTemplateId);
    addTemplate(saveTemplate);
  }
}