package net.sf.anathema.framework.presenter.view;

import javax.swing.ComboBoxEditor;
import javax.swing.JTextField;

public interface ITextFieldComboBoxEditor extends ComboBoxEditor {

  @Override
  JTextField getEditorComponent();
}