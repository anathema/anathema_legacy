package net.sf.anathema.character.equipment.character.view;

import net.disy.commons.core.model.BooleanModel;

public interface IEquipmentObjectView {

  public void setItemTitle(String title);

  public void setItemDescription(String text);

  public BooleanModel addStats(String description);
}