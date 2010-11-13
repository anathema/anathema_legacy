package net.sf.anathema.character.view.magic;

public interface IComboView {

  public void initGui(String name, String description);

  public void updateCombo(String name, String description);

  public void setEditText(String text);

  public void setEditButtonsVisible(boolean enabled);
}