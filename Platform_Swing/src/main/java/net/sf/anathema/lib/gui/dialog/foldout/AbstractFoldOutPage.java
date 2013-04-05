package net.sf.anathema.lib.gui.dialog.foldout;

import javax.swing.JComponent;

public abstract class AbstractFoldOutPage implements IFoldOutPage {
  private JComponent content;

  @Override
  public JComponent getContent() {
    if (content == null) {
      content = createContent();
    }
    return content;
  }

  protected abstract JComponent createContent();
}