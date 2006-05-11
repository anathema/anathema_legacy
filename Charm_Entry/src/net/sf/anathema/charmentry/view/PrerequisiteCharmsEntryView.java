package net.sf.anathema.charmentry.view;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.charmentry.presenter.view.IPrerequisiteCharmsEntryView;
import net.sf.anathema.lib.workflow.container.ISelectionContainerView;
import net.sf.anathema.lib.workflow.container.view.SelectionContainerListView;

public class PrerequisiteCharmsEntryView implements IPrerequisiteCharmsEntryView {

  private final JPanel content = new JPanel(new GridDialogLayout(1, false));

  public ISelectionContainerView addPrerequisiteCharmView(ListCellRenderer renderer) {
    SelectionContainerListView view = new SelectionContainerListView();
    view.setRenderer(renderer);
    content.add(new JScrollPane(
        view.getContent(),
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED), GridDialogLayoutData.FILL_VERTICAL);
    return view;
  }

  public JComponent getContent() {
    return content;
  }

  public void requestFocus() {
    // Nothing to do
  }

  public void dispose() {
    // Nothing to do
  }

  public JToggleButton addToggleButton(String label) {
    JCheckBox box = new JCheckBox(label);
    content.add(box);
    return box;
  }
}
