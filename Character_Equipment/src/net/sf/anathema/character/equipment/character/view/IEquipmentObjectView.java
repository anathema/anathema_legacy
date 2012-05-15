package net.sf.anathema.character.equipment.character.view;

import net.sf.anathema.lib.model.BooleanModel;

import javax.swing.Action;

public interface IEquipmentObjectView {

  public void setItemTitle(String title);

  public void setItemDescription(String text);
  
  public void clearContents();
  
  public BooleanModel addStats(String description);
  
  public BooleanModel addOptionFlag(BooleanModel base, String description);
  
  public void updateStatText(BooleanModel model, String newText);
  
  public void setEnabled(BooleanModel model, boolean enabled);

  public void addAction(Action action);
}