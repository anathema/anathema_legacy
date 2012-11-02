package net.sf.anathema.lib.gui.selection;

import net.sf.anathema.lib.gui.IView;

import javax.swing.ListCellRenderer;

public interface IListObjectSelectionView<V> extends
		IVetoableObjectSelectionView<V>, IView {

	void setCellRenderer(ListCellRenderer renderer);
}