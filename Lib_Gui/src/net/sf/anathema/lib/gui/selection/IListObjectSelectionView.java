package net.sf.anathema.lib.gui.selection;

import javax.swing.ListCellRenderer;

import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.list.ListSelectionMode;

public interface IListObjectSelectionView<V> extends
		IVetoableObjectSelectionView<V>, IView {

	public void setCellRenderer(ListCellRenderer renderer);

	public V[] getSelectedObjects();

	public void setSelectedObjects(V[] objects);

	public void setSelectionType(ListSelectionMode type);
}