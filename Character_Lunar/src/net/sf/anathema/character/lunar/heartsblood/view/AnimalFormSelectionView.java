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

  public AnimalFormSelectionView(
	      Icon icon,
	      String animalFormString,
	      String animalStrengthString,
	      String animalStaminaString)
  {
	  this(icon, animalFormString, animalStrengthString, null, animalStaminaString, null);
  }
  
  public AnimalFormSelectionView(
      Icon icon,
      String animalFormString,
      String animalStrengthString,
      String animalDexterityString,
      String animalStaminaString,
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
	int width = 7;
	if (animalDexterityString  != null) width += 2;
	if (animalAppearanceString != null) width += 2;
    JPanel panel = new JPanel(new GridDialogLayout(width, false));
    panel.add(new JLabel(animalFormString));
    panel.add(textField.getComponent());
    panel.add(new JLabel(animalStrengthString));
    panel.add(strengthSpinner.getComponent());
    if (animalDexterityString != null)
    {
    	panel.add(new JLabel(animalDexterityString));
        panel.add(dexteritySpinner.getComponent());	
    }
    panel.add(new JLabel(animalStaminaString));
    panel.add(staminaSpinner.getComponent());
    if (animalAppearanceString != null)
    {
    	panel.add(new JLabel(animalAppearanceString));
        panel.add(appearanceSpinner.getComponent());	
    }
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
  
  public void addDexterityListener(IIntValueChangedListener listener) {
	    dexteritySpinner.addChangeListener(listener);
	  }
  
  public void addAppearanceListener(IIntValueChangedListener listener) {
	    appearanceSpinner.addChangeListener(listener);
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
  
  public void setDexterity(int value) {
	    dexteritySpinner.setValue(value);
	  }
  
  public void setAppearance(int value) {
	    appearanceSpinner.setValue(value);
	  }
}