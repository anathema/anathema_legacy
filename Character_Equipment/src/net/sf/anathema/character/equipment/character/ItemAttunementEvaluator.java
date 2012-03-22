package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;

public interface ItemAttunementEvaluator {
  ArtifactAttuneType[] getAttuneTypes(IEquipmentItem item);
}