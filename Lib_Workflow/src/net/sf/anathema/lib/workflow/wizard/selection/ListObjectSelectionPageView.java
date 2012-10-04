package net.sf.anathema.lib.workflow.wizard.selection;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ListObjectSelectionView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ListObjectSelectionPageView<V> implements IObjectSelectionView<V> {

  private final JPanel panel = new JPanel(new MigLayout(new LC().fill()));
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
    panel.add(new JScrollPane(content), new CC().push().grow());
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
}