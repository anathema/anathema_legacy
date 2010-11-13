package net.sf.anathema.character.lunar.heartsblood.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

public class AnimalFormSelectionView implements IAnimalFormSelectionView, IView {

  private ITextView textField;
  private IntegerSpinner staminaSpinner;
  private String animalFormString;
  private String animalStrengthString;
  private String animalStaminaString;
  private JButton button;
  private IntegerSpinner strengthSpinner;

  public AnimalFormSelectionView(
      Icon icon,
      String animalFormString,
      String animalStrengthString,
      String animalStaminaString) {
    this.animalFormString = animalFormString;
    this.animalStrengthString = animalStrengthString;
    this.animalStaminaString = animalStaminaString;
    this.textField = new LineTextView(15);
    this.strengthSpinner = createIntegerSpinner();
    this.staminaSpinner = createIntegerSpinner();
    this.button = new JButton(null, icon);
    button.setPreferredSize(new Dimension(icon.getIconWidth() + 4, icon.getIconHeight() + 4));
  }

  private IntegerSpinner createIntegerSpinner() {
    IntegerSpinner spinner = new IntegerSpinner(1);
    spinner.setMaximum(99);
    spinner.setMinimum(1);
    spinner.setPreferredWidth(70);
    return spinner;
  }

  public JComponent getComponent() {
    JPanel panel = new JPanel(new GridDialogLayout(7, false));
    panel.add(new JLabel(animalFormString));
    panel.add(textField.getComponent());
    panel.add(new JLabel(animalStrengthString));
    panel.add(strengthSpinner.getComponent());
    panel.add(new JLabel(animalStaminaString));
    panel.add(staminaSpinner.getComponent());
    panel.add(button);
    return panel;
  }

  public void addNameListener(IObjectValueChangedListener<String> listener) {
    textField.addTextChangedListener(listener);
  }

  public void addStaminaListener(IIntValueChangedListener listener) {
    staminaSpinner.addChangeListener(listener);
  }

  public void addStrengthListener(IIntValueChangedListener listener) {
    strengthSpinner.addChangeListener(listener);
  }

  public void addAddButtonListener(ActionListener listener) {
    button.addActionListener(listener);
  }

  public void setAddButtonEnabled(boolean enabled) {
    button.setEnabled(enabled);
  }

  public void setName(String name) {
    textField.setText(name);
  }

  public void setStamina(int value) {
    staminaSpinner.setValue(value);
  }

  public void setStrength(int value) {
    strengthSpinner.setValue(value);
  }
}