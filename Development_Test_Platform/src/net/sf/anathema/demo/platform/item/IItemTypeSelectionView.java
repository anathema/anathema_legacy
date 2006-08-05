package net.sf.anathema.demo.platform.item;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;

public interface IItemTypeSelectionView extends IPageContent {

  public IListObjectSelectionView<CreationItemType> addSelectionView();

}
