package net.sf.anathema.character.equipment.item.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.character.equipment.ItemCost;
import net.sf.anathema.framework.value.IIntValueDisplayFactory;
import net.sf.anathema.character.generic.framework.intvalue.ISelectableIntValueView;
import net.sf.anathema.character.generic.framework.intvalue.SelectableIntValueView;
import net.sf.anathema.lib.gui.selection.ISelectionIntValueChangedListener;

public class CostSelectionView {
	private final ISelectableIntValueView<String> selection;
	protected final JLabel label;
	  
	public CostSelectionView(String labelText, String[] backgrounds, IIntValueDisplayFactory factory) {
	  this.label = new JLabel(labelText);
	  this.selection = new SelectableIntValueView<String>(factory, 0, 6);
	  
	  selection.setSelectableValues(backgrounds);
	}

	public void addTo(JPanel panel, IGridDialogLayoutData data) {
	  panel.add(label);
	  selection.addTo(panel);
	}
	
	public void setValue(ItemCost cost) {
		if (cost == null) {
			selection.setSelectedObject(null);
			selection.setValue(0);
		} else {
			selection.setSelectedObject(cost.getType());
			selection.setValue(cost.getValue());
		}
	}

	public void addSelectionChangedListener(final ISelectionIntValueChangedListener<String> listener) {
	  selection.addSelectionChangedListener(listener);
	}
}
