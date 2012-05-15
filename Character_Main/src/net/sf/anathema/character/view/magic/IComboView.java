package net.sf.anathema.character.view.magic;

public interface IComboView {

  void initGui(String name, String description);

  void updateCombo(String name, String description);

  void setEditText(String text);

  void setEditButtonsVisible(boolean enabled);
}