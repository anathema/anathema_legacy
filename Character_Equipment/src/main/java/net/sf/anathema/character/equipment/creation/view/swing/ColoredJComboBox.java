package net.sf.anathema.character.equipment.creation.view.swing;

import com.sun.java.swing.plaf.windows.WindowsComboBoxUI;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.metal.MetalComboBoxUI;
import java.awt.Color;

public class ColoredJComboBox extends JComboBox {

	public ColoredJComboBox(ComboBoxModel model) {
		super(model);
	}

	@Override
	public void updateUI() {
		ComboBoxUI comboBoxUI = (ComboBoxUI) UIManager.getUI(this);
		if (comboBoxUI instanceof MetalComboBoxUI) {
			comboBoxUI = new ColoredMetalComboBoxUI(Color.DARK_GRAY);
		} else if (comboBoxUI instanceof WindowsComboBoxUI) {
			comboBoxUI = new ColoredWindowsComboBoxUI(Color.DARK_GRAY);
		}
		setUI(comboBoxUI);
	}
}