package net.sf.anathema.demo.character.equipment.item;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;

public class DemoEquipmentDatabase implements IEquipmentDatabase {

  private Map<String, IEquipmentTemplate> templatesById = new HashMap<String, IEquipmentTemplate>();

  public String[] getAllAvailableTemplateIds() {
    Set<String> idSet = templatesById.keySet();
    return idSet.toArray(new String[idSet.size()]);
  }
}