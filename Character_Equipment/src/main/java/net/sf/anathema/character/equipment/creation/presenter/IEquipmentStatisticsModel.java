package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IEquipmentStatisticsModel {

  ITextualDescription getName();

  boolean isValid();
  
  void addValidListener(ChangeListener listener);
}