package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.lib.file.RelativePath;

import java.util.Collection;

public interface EquipmentItemRenderer {

  String getLabel(IEquipmentItem item);

  Collection<RelativePath> getIcons(IEquipmentItem item);
}
