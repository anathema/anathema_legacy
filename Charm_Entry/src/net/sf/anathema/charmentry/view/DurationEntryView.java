package net.sf.anathema.charmentry.view;

import javax.swing.JComponent;
import javax.swing.ListCellRenderer;

import net.sf.anathema.charmentry.presenter.view.IDurationEntryView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.container.factory.StandardPanelBuilder;

public class DurationEntryView implements IDurationEntryView {

  private final StandardPanelBuilder builder = new StandardPanelBuilder();

  public IObjectSelectionView addObjectSelectionView(String durationLabel, ListCellRenderer renderer, Object[] durations) {
    return builder.addEditableObjectSelectionView(durationLabel, renderer, durations);
  }

  public JComponent getContent() {
    return builder.getUntitledContent();
  }

  public void requestFocus() {
    // Nothing to do
  }

  public void dispose() {
    // Nothing to do
  }
}