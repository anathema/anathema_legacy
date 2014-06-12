package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;

import java.util.Collection;

public interface EquipmentItemRenderer {

  String getLabel(IEquipmentItem item);

  Collection<RelativePathWithDisabling> getIcons(IEquipmentItem item);
}