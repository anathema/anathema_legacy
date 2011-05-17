package net.sf.anathema.character.thaumaturgy.view;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.framework.presenter.view.ButtonControlledObjectSelectionView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;

public class ArtSelectionView extends ButtonControlledObjectSelectionView<String>
{

	public ArtSelectionView(
		      ListCellRenderer renderer,
		      Icon addIcon,
		      String labelText,
		      ITextFieldComboBoxEditor editor) {
		super(renderer, addIcon, labelText, editor);
	}

	/** GridDialogLayout, 2 columns */
	public void addComponents(JPanel panel) {
	  panel.add(comboBox, GridDialogLayoutData.FILL_HORIZONTAL);
	  panel.add(addButton, GridDialogLayoutData.RIGHT);
	  
	}
}
