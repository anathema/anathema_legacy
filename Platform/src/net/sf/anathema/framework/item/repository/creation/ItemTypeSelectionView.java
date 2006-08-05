package net.sf.anathema.framework.item.repository.creation;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ListObjectSelectionView;

public class ItemTypeSelectionView implements IItemTypeSelectionView {

  private final JPanel panel = new JPanel(new GridDialogLayout(1, false));
  private JComponent focusComponent;

  public IListObjectSelectionView<IItemType> addSelectionView() {
    ListObjectSelectionView<IItemType> view = new ListObjectSelectionView<IItemType>(IItemType.class);
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