package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.interaction.Tool;

public interface EquipmentObjectView {

  void setItemTitle(String title);

  void setItemDescription(String text);
  
  void clear();

  StatsView addStats(String description);

  Tool addAction();
}