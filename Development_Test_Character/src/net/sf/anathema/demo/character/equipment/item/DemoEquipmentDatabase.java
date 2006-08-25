package net.sf.anathema.demo.character.equipment.item;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.anathema.character.equipment.impl.character.model.natural.NaturalWeaponTemplate;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;

public class DemoEquipmentDatabase implements IEquipmentDatabase {

  private Map<String, IEquipmentTemplate> templatesById = new HashMap<String, IEquipmentTemplate>();

  public DemoEquipmentDatabase() {
    addTemplate(new NaturalWeaponTemplate());
  }

  private void addTemplate(IEquipmentTemplate template) {
    templatesById.put(template.getName(), template);
  }

  public String[] getAllAvailableTemplateIds() {
    Set<String> idSet = templatesById.keySet();
    return idSet.toArray(new String[idSet.size()]);
  }

  public IEquipmentTemplate loadTemplate(String templateId) {
    return templatesById.get(templateId);
  }
}