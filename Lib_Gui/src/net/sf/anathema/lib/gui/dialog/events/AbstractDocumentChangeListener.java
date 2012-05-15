package net.sf.anathema.lib.gui.dialog.events;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class AbstractDocumentChangeListener implements DocumentListener {

  @Override
  public void insertUpdate(final DocumentEvent e) {
    documentChanged();
  }

  @Override
  public void removeUpdate(final DocumentEvent e) {
    documentChanged();
  }

  @Override
  public void changedUpdate(final DocumentEvent e) {
    documentChanged();
  }

  protected abstract void documentChanged();
}