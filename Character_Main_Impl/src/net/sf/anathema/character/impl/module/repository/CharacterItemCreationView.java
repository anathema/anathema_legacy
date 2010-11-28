package net.sf.anathema.character.impl.module.repository;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.util.LayoutUtilities;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.impl.module.IToggleButtonPanel;
import net.sf.anathema.character.impl.module.ToggleButtonPanel;
import net.sf.anathema.character.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;

public class CharacterItemCreationView implements ICharacterItemCreationView {

  private final JPanel component;

  public CharacterItemCreationView() {
    int horizontalSpacing = LayoutUtilities.getDpiAdjusted(10);
    int vertialSpacing = LayoutUtilities.getComponentSpacing();
    this.component = new JPanel(new GridDialogLayout(2, false, horizontalSpacing, vertialSpacing));
  }

  public IToggleButtonPanel addToggleButtonPanel() {
    ToggleButtonPanel panel = new ToggleButtonPanel();
    GridDialogLayoutData data = GridDialogLayoutDataUtilities.createFillNoGrab();
    data.setGrabExcessVerticalSpace(true);
    data.setVerticalSpan(2);
    JComponent content = panel.getComponent();
    component.add(content, data);
    return panel;
  }

  public JComponent getContent() {
    return component;
  }

  public void requestFocus() {
    // nothing to do
  }

  public void dispose() {
    // nothing to do
  }

  public IListObjectSelectionView<ITemplateTypeAggregation> addObjectSelectionList() {
    ListObjectSelectionView<ITemplateTypeAggregation> view = new ListObjectSelectionView<ITemplateTypeAggregation>(
        ITemplateTypeAggregation.class);
    JScrollPane scrollPane = new JScrollPane(view.getComponent());
    component.add(scrollPane, GridDialogLayoutData.FILL_BOTH);
    return view;
  }

  public ObjectSelectionView<IExaltedRuleSet> addRulesetSelectionView(
      String label,
      ListCellRenderer renderer,
      IExaltedRuleSet[] objects) {
    ObjectSelectionView<IExaltedRuleSet> view = new ObjectSelectionView<IExaltedRuleSet>(
        label,
        renderer,
        false,
        objects);
    JPanel panel = new JPanel(new GridDialogLayout(2, false));
    view.addTo(panel, GridDialogLayoutData.FILL_HORIZONTAL);
    component.add(panel, GridDialogLayoutData.FILL_HORIZONTAL);
    return view;
  }
}