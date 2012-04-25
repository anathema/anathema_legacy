package net.sf.anathema.lib.workflow.wizard.selection;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ListObjectSelectionView;

public class ListObjectSelectionPageView<V> implements IObjectSelectionView<V> {

  private final JPanel panel = new JPanel(new GridDialogLayout(1, false));
  private JComponent focusComponent;
  private final Class<V> clazz;

  public ListObjectSelectionPageView(Class<V> clazz) {
    this.clazz = clazz;
  }

  @Override
  public IListObjectSelectionView<V> addSelectionView() {
    ListObjectSelectionView<V> view = new ListObjectSelectionView<V>(clazz);
    JComponent content = view.getComponent();
    this.focusComponent = content;
    panel.add(new JScrollPane(content), GridDialogLayoutData.FILL_BOTH);
    return view;
  }

  @Override
  public JComponent getContent() {
    return panel;
  }

  @Override
  public void requestFocus() {
    if (focusComponent != null) {
      focusComponent.requestFocus();
    }
  }

  @Override
  public void dispose() {
    //nothing to do;
  }
}