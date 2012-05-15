package net.sf.anathema.lib.workflow.wizard.selection;

import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;

public interface IObjectSelectionView<V> extends IPageContent {

  IListObjectSelectionView<V> addSelectionView();
}