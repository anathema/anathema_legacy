package net.sf.anathema.charmentry.view;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.charmentry.presenter.view.IPrerequisiteCharmsEntryView;
import net.sf.anathema.lib.workflow.container.ISelectionContainerView;
import net.sf.anathema.lib.workflow.container.view.SelectionContainerListView;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;

public class PrerequisiteCharmsEntryView implements IPrerequisiteCharmsEntryView {

  private final JPanel content = new JPanel(new GridDialogLayout(1, false));

  @Override
  public ISelectionContainerView<ICharm> addPrerequisiteCharmView(ListCellRenderer renderer) {
    SelectionContainerListView<ICharm> view = new SelectionContainerListView<ICharm>(ICharm.class);
    view.setRenderer(renderer);
    content.add(new JScrollPane(
        view.getComponent(),
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED), GridDialogLayoutData.FILL_VERTICAL);
    return view;
  }

  @Override
  public JComponent getContent() {
    return content;
  }

  @Override
  public void requestFocus() {
    // Nothing to do
  }

  @Override
  public JToggleButton addToggleButton(String label) {
    JCheckBox box = new JCheckBox(label);
    content.add(box);
    return box;
  }
}
