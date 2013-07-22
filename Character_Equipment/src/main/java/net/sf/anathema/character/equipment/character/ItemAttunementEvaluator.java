package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.hero.equipment.sheet.content.stats.ArtifactAttuneType;

public interface ItemAttunementEvaluator {
  ArtifactAttuneType[] getAttuneTypes(IEquipmentItem item);
}