package net.sf.anathema.character.presenter.util;

import java.awt.event.ActionListener;

import javax.swing.ComboBoxEditor;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxEditor;

import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;

public class ProxyComboBoxEditor implements ITextFieldComboBoxEditor {

  private ComboBoxEditor editor = new BasicComboBoxEditor();
  
  public JTextField getEditorComponent() {
    return (JTextField) editor.getEditorComponent();
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
