package net.sf.anathema.character.lunar.heartsblood.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.workflow.textualdescription.SwingTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class AnimalFormSelectionView implements IAnimalFormSelectionView {
  private SwingTextView textField;
  private IntegerSpinner strengthSpinner;
  private IntegerSpinner dexteritySpinner;
  private IntegerSpinner staminaSpinner;
  private IntegerSpinner appearanceSpinner;
  private String animalFormString;
  private String animalStrengthString;
  private String animalDexterityString;
  private String animalStaminaString;
  private String animalAppearanceString;
  private JButton button;

  public AnimalFormSelectionView(Icon icon, String animalFormString, String animalStrengthString,
                                 String animalDexterityString, String animalStaminaString,
                                 String animalAppearanceString) {
    this.animalFormString = animalFormString;
    this.animalStrengthString = animalStrengthString;
    this.animalDexterityString = animalDexterityString;
    this.animalStaminaString = animalStaminaString;
    this.animalAppearanceString = animalAppearanceString;
    this.textField = new LineTextView(15);
    this.strengthSpinner = createIntegerSpinner();
    this.dexteritySpinner = createIntegerSpinner();
    this.staminaSpinner = createIntegerSpinner();
    this.appearanceSpinner = createIntegerSpinner();
    this.button = new JButton(null, icon);
    button.setPreferredSize(new Dimension(icon.getIconWidth() + 4, icon.getIconHeight() + 4));
  }

  private IntegerSpinner createIntegerSpinner() {
    IntegerSpinner spinner = new IntegerSpinner(1);
    spinner.setMaximum(99);
    spinner.setMinimum(1);
    spinner.setPreferredWidth(40);
    return spinner;
  }

  public JComponent getComponent() {
    JPanel panel = new JPanel(new MigLayout(withoutInsets()));
    panel.add(new JLabel(animalFormString));
    panel.add(textField.getComponent(), new CC().growX().pushX());
    panel.add(new JLabel(animalStrengthString));
    panel.add(strengthSpinner.getComponent());
    panel.add(new JLabel(animalDexterityString));
    panel.add(dexteritySpinner.getComponent());
    panel.add(new JLabel(animalStaminaString));
    panel.add(staminaSpinner.getComponent());
    panel.add(new JLabel(animalAppearanceString));
    panel.add(appearanceSpinner.getComponent());
    panel.add(button, LayoutUtils.constraintsForImageButton(button));
    return panel;
  }

  @Override
  public void addNameListener(ObjectValueListener<String> listener) {
    textField.addTextChangedListener(listener);
  }

  @Override
  public void addStaminaListener(IIntValueChangedListener listener) {
    staminaSpinner.addChangeListener(listener);
  }

  @Override
  public void addStrengthListener(IIntValueChangedListener listener) {
    strengthSpinner.addChangeListener(listener);
  }

  @Override
  public void addDexterityListener(IIntValueChangedListener listener) {
    dexteritySpinner.addChangeListener(listener);
  }

  @Override
  public void addAppearanceListener(IIntValueChangedListener listener) {
    appearanceSpinner.addChangeListener(listener);
  }

  @Override
  public void addAddButtonListener(ActionListener listener) {
    button.addActionListener(listener);
  }

  @Override
  public void setAddButtonEnabled(boolean enabled) {
    button.setEnabled(enabled);
  }

  @Override
  public void setName(String name) {
    textField.setText(name);
  }

  @Override
  public void setStamina(int value) {
    staminaSpinner.setValue(value);
  }

  @Override
  public void setStrength(int value) {
    strengthSpinner.setValue(value);
  }

  @Override
  public void setDexterity(int value) {
    dexteritySpinner.setValue(value);
  }

  @Override
  public void setAppearance(int value) {
    appearanceSpinner.setValue(value);
  }
}