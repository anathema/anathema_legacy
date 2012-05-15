package net.sf.anathema.character.model.charm;

public interface IComboConfigurationListener {

  void comboAdded(ICombo combo);

  void comboChanged(ICombo combo);

  void comboDeleted(ICombo combo);

  void editBegun(ICombo combo);

  void editEnded();
}