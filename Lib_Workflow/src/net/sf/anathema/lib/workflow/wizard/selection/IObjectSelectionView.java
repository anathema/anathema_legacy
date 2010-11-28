package net.sf.anathema.lib.workflow.wizard.selection;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;

public interface IObjectSelectionView<V> extends IPageContent {

  public IListObjectSelectionView<V> addSelectionView();
}