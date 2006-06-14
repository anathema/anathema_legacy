package net.sf.anathema.charmentry.view;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.sf.anathema.charmentry.presenter.view.IHeaderDataEntryView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.container.factory.StandardPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class HeaderDataEntryView implements IHeaderDataEntryView {

  private final StandardPanelBuilder builder = new StandardPanelBuilder();
  private JPanel content;
  private JComponent focusComponent;

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
    final ITextView textView = builder.addLineTextView(label, 15);
    if (focusComponent == null) {
      focusComponent = textView.getComponent();
    }
    return textView;
  }

  public ISourceSelectionView addSourceView(String bookLabel, String pageLabel, Object[] predefinedSources, ListCellRenderer renderer) {
    SourceSelectionView view = new SourceSelectionView(bookLabel, pageLabel, predefinedSources);
    view.setRenderer(renderer);
    builder.addDialogComponent(view);
    return view;
  }

  public void requestFocus() {
    focusComponent.requestFocus();
  }

  public void dispose() {
    // Nothing to do
  }
}