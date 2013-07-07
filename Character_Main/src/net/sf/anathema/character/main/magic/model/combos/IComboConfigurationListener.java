package net.sf.anathema.character.main.magic.model.combos;

public interface IComboConfigurationListener {

  void comboAdded(ICombo combo);

  void comboChanged(ICombo combo);

  void comboDeleted(ICombo combo);

  void editBegun(ICombo combo);

  void editEnded();
}