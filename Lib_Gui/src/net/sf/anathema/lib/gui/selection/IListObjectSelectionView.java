package net.sf.anathema.lib.gui.selection;

import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.list.ListSelectionMode;

import javax.swing.ListCellRenderer;

public interface IListObjectSelectionView<V> extends
		IVetoableObjectSelectionView<V>, IView {

	void setCellRenderer(ListCellRenderer renderer);

	V[] getSelectedObjects();

	void setSelectedObjects(V[] objects);

	void setSelectionType(ListSelectionMode type);
}