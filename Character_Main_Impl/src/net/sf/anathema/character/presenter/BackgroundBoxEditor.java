package net.sf.anathema.character.presenter;

import net.sf.anathema.character.library.util.ProxyComboBoxEditor;

public class BackgroundBoxEditor extends ProxyComboBoxEditor {
  private Displayer displayer;

  public BackgroundBoxEditor(Displayer displayer) {
    this.displayer = displayer;
  }

  @Override
  public void setItem(Object anObject) {
    super.setItem(displayer.getDisplayObject(anObject));
  }
}