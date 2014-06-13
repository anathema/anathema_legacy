package net.sf.anathema.hero.equipment.display.presenter;

public interface EquipmentObjectView extends PersonalizationEditView {

  void setItemTitle(String title);

  void setItemDescription(String text);

  void clear();

  void clearStats();

  StatsView addStats(String description);

  void enablePersonalization();

  void disablePersonalization();
}