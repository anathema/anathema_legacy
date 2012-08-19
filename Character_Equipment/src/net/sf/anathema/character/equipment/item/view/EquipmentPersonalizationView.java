package net.sf.anathema.character.equipment.item.view;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EquipmentPersonalizationView implements IPageContent, IEquipmentPersonalizationView {

	private final JPanel content = new JPanel(new GridDialogLayout(2, false));
	private JComponent focusComponent;
	
	@Override
	public JComponent getContent() {
		JPanel panel = new JPanel(new GridDialogLayout(1, false));
		IGridDialogLayoutData data = GridDialogLayoutData.FILL_HORIZONTAL;
		panel.add(content, data);
		return panel;
	}
	
	@Override
	public void addEntry(String label, JTextField text) {
		content.add(new JLabel(label));
		content.add(text, GridDialogLayoutData.FILL_HORIZONTAL);
		if (focusComponent == null) {
			focusComponent = text;
		}
	}

	@Override
	public void requestFocus() {
	    focusComponent.requestFocus();
	}
}
