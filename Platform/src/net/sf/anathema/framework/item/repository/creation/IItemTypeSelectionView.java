package net.sf.anathema.framework.item.repository.creation;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;

public interface IItemTypeSelectionView extends IPageContent {

  public IListObjectSelectionView<IItemType> addSelectionView();

}
