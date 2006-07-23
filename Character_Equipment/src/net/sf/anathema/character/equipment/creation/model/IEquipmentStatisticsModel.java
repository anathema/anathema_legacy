package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IEquipmentStatisticsModel {

  public BooleanValueModel getNameSpecified();

  public ITextualDescription getName();
}