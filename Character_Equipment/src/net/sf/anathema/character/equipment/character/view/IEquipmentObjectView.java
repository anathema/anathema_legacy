package net.sf.anathema.character.equipment.character.view;

import net.sf.anathema.lib.model.BooleanModel;

import javax.swing.Action;

public interface IEquipmentObjectView {

  void setItemTitle(String title);

  void setItemDescription(String text);
  
  void clearContents();
  
  BooleanModel addStats(String description);
  
  BooleanModel addOptionFlag(BooleanModel base, String description);
  
  void updateStatText(BooleanModel model, String newText);
  
  void setEnabled(BooleanModel model, boolean enabled);

  void addAction(Action action);
}