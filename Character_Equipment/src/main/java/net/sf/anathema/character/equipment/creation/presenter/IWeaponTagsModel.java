package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;

public interface IWeaponTagsModel {

  IWeaponTag[] getAllTags();

  BooleanValueModel getEnabledModel(IWeaponTag tag);

  BooleanValueModel getSelectedModel(IWeaponTag tag);

  void setTagsCloseCombatStyle();

  void setTagsRangedCombatStyle();

  IWeaponTag[] getSelectedTags();

  boolean isRangedTypeTagSelected();
  
  boolean isThrownTypeTagSelected();
  
  boolean isThrownWeaponTagSelected();
}