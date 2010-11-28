package net.sf.anathema.character.model.charm;

public interface IComboConfigurationListener {

  public void comboAdded(ICombo combo);

  public void comboChanged(ICombo combo);

  public void comboDeleted(ICombo combo);

  public void editBegun(ICombo combo);

  public void editEnded();
}