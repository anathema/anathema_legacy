package net.sf.anathema.lib.gui.dialog.events;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class AbstractDocumentChangeListener implements DocumentListener {

  @Override
  public void insertUpdate(DocumentEvent e) {
    documentChanged();
  }

  @Override
  public void removeUpdate(DocumentEvent e) {
    documentChanged();
  }

  @Override
  public void changedUpdate(DocumentEvent e) {
    documentChanged();
  }

  protected abstract void documentChanged();
}