package net.sf.anathema.character.equipment.creation.model;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IEquipmentStatisticsModel {

  public BooleanModel isNameSpecified();

  public ITextualDescription getName();
}