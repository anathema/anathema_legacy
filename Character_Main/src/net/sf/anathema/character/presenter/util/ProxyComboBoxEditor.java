package net.sf.anathema.character.presenter.util;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;

public class ProxyComboBoxEditor implements ComboBoxEditor {

  private ComboBoxEditor editor = new JComboBox().getEditor();
  
  public Component getEditorComponent() {
    return editor.getEditorComponent();
  }

  public void setItem(Object anObject) {
    editor.setItem(anObject);
  }

  public Object getItem() {
    return editor.getItem();
  }

  public void selectAll() {
    editor.selectAll();
  }

  public void addActionListener(ActionListener l) {
    editor.addActionListener(l);
  }

  public void removeActionListener(ActionListener l) {
    editor.addActionListener(l);
  }
}
