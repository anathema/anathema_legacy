package net.sf.anathema.lib.gui.widgets;

import java.awt.Dimension;
import java.text.DecimalFormat;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.intvalue.IntValueControl;

public class IntegerSpinner {

  private int value;
  private final JSpinner spinner;
  private final IntValueControl control = new IntValueControl();

  public IntegerSpinner(int initialValue) {
    spinner = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    DecimalFormat decimalFormat = new DecimalFormat();
    decimalFormat.setGroupingSize(0);
    final JSpinner.NumberEditor numberEditor = new JSpinner.NumberEditor(spinner, decimalFormat.toPattern());
    initDigitsOnlyDocument(numberEditor.getTextField());
    spinner.setEditor(numberEditor);
    numberEditor.getTextField().setValue(initialValue);
  }

  public void setMaximum(Integer maximum) {
    getSpinnerModel().setMaximum(maximum);
  }

  public void setMinimum(Integer minimum) {
    getSpinnerModel().setMinimum(minimum);
  }

  public void setStepSize(Integer stepsize) {
    getSpinnerModel().setStepSize(stepsize);
  }

  private SpinnerNumberModel getSpinnerModel() {
    return ((SpinnerNumberModel) spinner.getModel());
  }

  public JComponent getComponent() {
    return spinner;
  }

  public void addChangeListener(IIntValueChangedListener listener) {
    control.addIntValueChangeListener(listener);
  }

  private void fireChangeEvent(int newValue) {
    control.fireValueChangedEvent(newValue);
  }

  private void initDigitsOnlyDocument(final JTextComponent textField) {
    DigitsOnlyDocument document = new DigitsOnlyDocument();
    textField.setDocument(document);
    document.addDocumentListener(new DocumentListener() {
      String oldText;

      public void changedUpdate(DocumentEvent evt) {
        // Nothing to do
      }

      public void insertUpdate(DocumentEvent evt) {
        try {
          oldText = textField.getText();
          value = Integer.parseInt(oldText);
          fireChangeEvent(value);
        }
        catch (NumberFormatException exc) {
          textField.getDocument().removeDocumentListener(this);
          DigitsOnlyDocument newDocument = new DigitsOnlyDocument();
          textField.setDocument(newDocument);
          newDocument.addDocumentListener(this);
          textField.setText(String.valueOf(value));
          textField.requestFocus();
        }
      }

      public void removeUpdate(DocumentEvent evt) {
        oldText = textField.getText();
      }
    });
  }

  public int getValue() {
    return value;
  }

  public void setValue(Object newValue) {
    spinner.setValue(newValue);
  }

  public void setPreferredWidth(int width) {
    spinner.setPreferredSize(new Dimension(width, spinner.getPreferredSize().height));
  }

  public void removeChangeListener(IIntValueChangedListener listener) {
    control.removeIntValueChangeListener(listener);
  }

  public void setEditable(boolean editable) {
    NumberEditor editor = (NumberEditor) spinner.getEditor();
    editor.getTextField().setEditable(editable);
  }
}