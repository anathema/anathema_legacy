package net.sf.anathema.charmentry.presenter.view;

import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;

import javax.swing.ListCellRenderer;

public interface ISimpleSpecialsView extends IPageContent {

  public IIntValueView addIntegerSelectionView(String speedLabel, int maximum);

  public <V> IObjectSelectionView<V> addObjectSelectionView(String labelString, ListCellRenderer renderer, V[] objects);
}