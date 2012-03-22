package net.sf.anathema.character.impl.module.repository;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.disy.commons.swing.layout.util.LayoutUtilities;
import net.sf.anathema.character.impl.module.IToggleButtonPanel;
import net.sf.anathema.character.impl.module.ToggleButtonPanel;
import net.sf.anathema.character.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ListObjectSelectionView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CharacterItemCreationView implements ICharacterItemCreationView {

  private final JPanel component;

  public CharacterItemCreationView() {
    int horizontalSpacing = LayoutUtilities.getDpiAdjusted(10);
    int vertialSpacing = LayoutUtilities.getComponentSpacing();
    this.component = new JPanel(new GridDialogLayout(2, false, horizontalSpacing, vertialSpacing));
  }

  @Override
  public IToggleButtonPanel addToggleButtonPanel() {
    ToggleButtonPanel panel = new ToggleButtonPanel();
    GridDialogLayoutData data = GridDialogLayoutDataFactory.createFillNoGrab();
    data.setGrabExcessVerticalSpace(true);
    JComponent content = panel.getComponent();
    component.add(content, data);
    return panel;
  }

  @Override
  public JComponent getContent() {
    return component;
  }

  @Override
  public void requestFocus() {
    // nothing to do
  }

  @Override
  public void dispose() {
    // nothing to do
  }

  @Override
  public IListObjectSelectionView<ITemplateTypeAggregation> addObjectSelectionList() {
    ListObjectSelectionView<ITemplateTypeAggregation> view = new ListObjectSelectionView<ITemplateTypeAggregation>(
        ITemplateTypeAggregation.class);
    JScrollPane scrollPane = new JScrollPane(view.getComponent());
    component.add(scrollPane, GridDialogLayoutData.FILL_BOTH);
    return view;
  }
}