package net.sf.anathema.character.equipment.character.view;

import javax.swing.Action;

import net.disy.commons.core.model.BooleanModel;

public interface IEquipmentObjectView {

  public void setItemTitle(String title);

  public void setItemDescription(String text);
  
  public BooleanModel addStats(String description);
  
  public void updateStatText(BooleanModel model, String newText);
  
  public void setEnabled(BooleanModel model, boolean enabled);

  public void addAction(Action action);
}