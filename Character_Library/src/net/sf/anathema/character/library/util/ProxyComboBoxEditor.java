package net.sf.anathema.character.library.util;

import java.awt.event.ActionListener;

import javax.swing.ComboBoxEditor;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxEditor;

import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;

public class ProxyComboBoxEditor implements ITextFieldComboBoxEditor {

  private final ComboBoxEditor editor = new BasicComboBoxEditor();

  @Override
  public JTextField getEditorComponent() {
    return (JTextField) editor.getEditorComponent();
  }

  @Override
  public void setItem(Object anObject) {
    editor.setItem(anObject);
  }

  @Override
  public Object getItem() {
    return editor.getItem();
  }

  @Override
  public void selectAll() {
    editor.selectAll();
  }

  @Override
  public void addActionListener(ActionListener l) {
    editor.addActionListener(l);
  }

  @Override
  public void removeActionListener(ActionListener l) {
    editor.addActionListener(l);
  }
}