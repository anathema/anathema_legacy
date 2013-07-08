package net.sf.anathema.lib.gui.widgets;

import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.control.IntValueChangedListener;
import net.sf.anathema.lib.data.IOverline;
import net.sf.anathema.lib.data.Range;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class IntegerSpinner implements IView, IIntegerView {

  private final JSpinner spinner;
  private final Map<IntValueChangedListener, ChangeListener> listenerMap = new HashMap<>();
  private final SpinnerNumberModel numberModel;

  public IntegerSpinner(int initialValue) {
    numberModel = new SpinnerNumberModel(initialValue, null, null, 1);
    spinner = new JSpinner(numberModel);
    DecimalFormat decimalFormat = new DecimalFormat();
    decimalFormat.setGroupingSize(0);
    JSpinner.NumberEditor numberEditor = new JSpinner.NumberEditor(spinner, decimalFormat.toPattern());
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

  private SpinnerNumberModel getSpinnerModel() {
    return (SpinnerNumberModel) spinner.getModel();
  }

  @Override
  public JComponent getComponent() {
    return spinner;
  }

  @Override
  public void addChangeListener(final IntValueChangedListener listener) {
    ChangeListener changeListener = new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        listener.valueChanged(getValue());
      }
    };
    listenerMap.put(listener, changeListener);
    numberModel.addChangeListener(changeListener);
  }

  private void initDigitsOnlyDocument(final JTextComponent textField) {
    DigitsOnlyDocument document = new DigitsOnlyDocument(true, new IOverline() {
      @Override
      public int getNearestValue(int value) {
        return createRange().getNearestValue(value);
      }

      @Override
      public int getLowerBound() {
        return createRange().getLowerBound();
      }

      private Range createRange() {
        Integer maximum = (Integer) getSpinnerModel().getMaximum();
        Integer minimum = (Integer) getSpinnerModel().getMinimum();
        return new Range(minimum, maximum);
      }
    });
    textField.setDocument(document);
    document.addDocumentListener(new DocumentListener() {

      @Override
      public void changedUpdate(DocumentEvent evt) {
        updateSpinnerModel();
      }

      @Override
      public void insertUpdate(DocumentEvent evt) {
        updateSpinnerModel();
      }

      private void updateSpinnerModel() {
        try {
          String newText = textField.getText();
          int value = Integer.parseInt(newText);
          numberModel.setValue(value);
        } catch (NumberFormatException exc) {
          // nothing to do
        }
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        updateSpinnerModel();
      }
    });
  }

  public int getValue() {
    return ((Number) numberModel.getValue()).intValue();
  }

  public void setValue(Object newValue) {
    spinner.setValue(newValue);
  }

  public void setPreferredWidth(int width) {
    spinner.setPreferredSize(new Dimension(width, spinner.getPreferredSize().height));
  }

  @SuppressWarnings("UnusedDeclaration")
  public void removeChangeListener(IntValueChangedListener listener) {
    numberModel.removeChangeListener(listenerMap.get(listener));
  }
}