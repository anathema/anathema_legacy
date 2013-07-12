package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.model.BooleanModel;

public interface EquipmentObjectView {

  void setItemTitle(String title);

  void setItemDescription(String text);
  
  void clearContents();
  
  BooleanModel addStats(String description);
  
  BooleanModel addOptionFlag(BooleanModel base, String description);

  void setEnabled(BooleanModel model, boolean enabled);

  Tool addAction();
}