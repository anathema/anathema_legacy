package net.sf.anathema.character.equipment.creation.model.stats;

import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;

public interface IWeaponTagsModel {

  public IWeaponTag[] getAllTags();

  public BooleanValueModel getEnabledModel(IWeaponTag tag);

  public BooleanValueModel getSelectedModel(IWeaponTag tag);

  public void setTagsCloseCombatStyle();

  public void setTagsRangedCombatStyle();

  public IWeaponTag[] getSelectedTags();

  public boolean isRangedTypeTagSelected();
  
  public boolean isThrownTypeTagSelected();
  
  public boolean isThrownWeaponTagSelected();
}