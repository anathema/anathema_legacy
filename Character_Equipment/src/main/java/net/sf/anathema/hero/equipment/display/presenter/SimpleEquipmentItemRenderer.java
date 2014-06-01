package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.lib.file.RelativePath;

import java.util.Collection;
import java.util.Collections;

public class SimpleEquipmentItemRenderer implements EquipmentItemRenderer {
  @Override
  public String getLabel(IEquipmentItem item) {
    return item.getTemplateId();
  }

  @Override
  public Collection<RelativePath> getIcons(IEquipmentItem item) {
    return Collections.emptyList();
  }
}
