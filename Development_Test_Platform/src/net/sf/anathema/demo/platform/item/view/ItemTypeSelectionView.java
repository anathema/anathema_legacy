package net.sf.anathema.demo.platform.item.view;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.demo.platform.item.CreationItemType;
import net.sf.anathema.demo.platform.item.IItemTypeSelectionView;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ListObjectSelectionView;

public class ItemTypeSelectionView implements IItemTypeSelectionView {

  private final JPanel panel = new JPanel(new GridDialogLayout(1, false));
  private JComponent focusComponent;

  public IListObjectSelectionView<CreationItemType> addSelectionView() {
    ListObjectSelectionView<CreationItemType> view = new ListObjectSelectionView<CreationItemType>(
        CreationItemType.class);
    JComponent content = view.getContent();
    this.focusComponent = content;
    panel.add(new JScrollPane(content), GridDialogLayoutData.FILL_BOTH);
    return view;
  }

  public JComponent getContent() {
    return panel;
  }

  public void requestFocus() {
    if (focusComponent != null) {
      focusComponent.requestFocus();
    }
  }

  public void dispose() {
    //nothing to do;
  }
}