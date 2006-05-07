package net.sf.anathema.charmentry.demo.view;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.sf.anathema.charmentry.demo.IHeaderDataEntryView;
import net.sf.anathema.charmentry.view.ISourceSelectionView;
import net.sf.anathema.charmentry.view.SourceSelectionView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.container.factory.StandardPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class HeaderDataEntryView implements IHeaderDataEntryView {

  private final StandardPanelBuilder builder = new StandardPanelBuilder();
  private JPanel content;

  public JComponent getContent() {
    if (content == null) {
      content = builder.getUntitledContent();
    }
    return content;
  }

  public IObjectSelectionView addComboBoxRow(String label, ListCellRenderer renderer, Object[] objects) {
    return builder.addObjectSelectionView(label, renderer, objects);
  }

  public ITextView addLineTextRow(String label) {
    return builder.addLineTextView(label, 15);
  }

  public ISourceSelectionView addSourceView(String bookLabel, String pageLabel, Object[] predefinedSources) {
    SourceSelectionView view = new SourceSelectionView(bookLabel, pageLabel, predefinedSources);
    builder.addDialogComponent(view);
    return view;
  }

  public void requestFocus() {
    // Nothing to do
  }

  public void dispose() {
    // Nothing to do
  }
}