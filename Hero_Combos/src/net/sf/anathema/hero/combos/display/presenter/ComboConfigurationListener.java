package net.sf.anathema.hero.combos.display.presenter;

public interface ComboConfigurationListener {

  void comboAdded(Combo combo);

  void comboChanged(Combo combo);

  void comboDeleted(Combo combo);

  void editBegun(Combo combo);

  void editEnded();
}