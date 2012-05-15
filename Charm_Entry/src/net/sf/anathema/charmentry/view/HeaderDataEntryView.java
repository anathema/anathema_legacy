package net.sf.anathema.charmentry.view;

import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.charmentry.presenter.view.IHeaderDataEntryView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.container.factory.StandardPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class HeaderDataEntryView implements IHeaderDataEntryView {

  private final StandardPanelBuilder builder = new StandardPanelBuilder();
  private JPanel content;
  private JComponent focusComponent;

  @Override
  public JComponent getContent() {
    if (content == null) {
      content = builder.getUntitledContent();
    }
    return content;
  }

  @Override
  public <V> IObjectSelectionView<V> addComboBoxRow(String label, ListCellRenderer renderer, V[] objects) {
    return builder.addObjectSelectionView(label, renderer, objects);
  }

  @Override
  public ITextView addLineTextRow(String label) {
    ITextView textView = builder.addLineTextView(label, 15);
    if (focusComponent == null) {
      focusComponent = textView.getComponent();
    }
    return textView;
  }

  @Override
  public ISourceSelectionView addSourceView(
      String bookLabel,
      String pageLabel,
      IExaltedSourceBook[] predefinedSources,
      ListCellRenderer renderer) {
    SourceSelectionView view = new SourceSelectionView(bookLabel, pageLabel, predefinedSources);
    view.setRenderer(renderer);
    builder.addDialogComponent(view);
    return view;
  }

  @Override
  public void requestFocus() {
    focusComponent.requestFocus();
  }

  @Override
  public void dispose() {
    // Nothing to do
  }
}