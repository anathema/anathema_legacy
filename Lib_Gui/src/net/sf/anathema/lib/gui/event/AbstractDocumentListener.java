package net.sf.anathema.lib.gui.event;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class AbstractDocumentListener implements DocumentListener {

  public final void insertUpdate(DocumentEvent e) {
    updateText(e);
  }

  public final void removeUpdate(DocumentEvent e) {
    updateText(e);
  }

  public final void changedUpdate(DocumentEvent e) {
    updateText(e);
  }

  protected abstract void updateText(DocumentEvent e);

}
