package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.model.IModifiableBooleanModel;

public interface EquipmentObjectView {

  void setItemTitle(String title);

  void setItemDescription(String text);
  
  void clear();

  IModifiableBooleanModel addStats(String description);

  IModifiableBooleanModel addOptionFlag(IModifiableBooleanModel base, String description);

  void setEnabled(IModifiableBooleanModel model, boolean enabled);

  Tool addAction();
}