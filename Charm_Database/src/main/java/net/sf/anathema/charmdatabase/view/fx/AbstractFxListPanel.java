package net.sf.anathema.charmdatabase.view.fx;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.platform.fx.ListSelectionView;

public abstract class AbstractFxListPanel<T> extends AbstractFxPane {

	private ListSelectionView<T> listView = new ListSelectionView<>();
	
	protected AbstractFxListPanel(LC layout, AC col, AC row, IconlessCellRenderer<T> renderer) {
		super(layout, col, row);
		listView.setCellRenderer(renderer);
		addToPane(listView.getNode(), new CC());
	}
	
	protected void setObjects(T[] objects) {
		listView.setObjects(objects);
	}
}
