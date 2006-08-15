package net.sf.anathema.character.equipment.creation.model.stats;

import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;

public interface IWeaponTagsModel {

  public IWeaponTag[] getAllTags();

  public BooleanValueModel getBooleanModel(IWeaponTag tag);

}