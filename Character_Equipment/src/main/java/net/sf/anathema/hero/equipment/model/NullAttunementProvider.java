package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.character.equipment.character.ItemAttunementEvaluator;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.hero.equipment.sheet.content.stats.ArtifactAttuneType;

public class NullAttunementProvider implements ItemAttunementEvaluator {
  @Override
  public ArtifactAttuneType[] getAttuneTypes(IEquipmentItem item) {
    return new ArtifactAttuneType[0];
  }
}