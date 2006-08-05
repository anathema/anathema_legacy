package net.sf.anathema.character.impl.module;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;

public class CharacterItemCreationView implements ICharacterItemCreationView {

  private final JPanel component = new JPanel(new GridDialogLayout(2, false));

  public IToggleButtonPanel addToggleButtonPanel() {
    ToggleButtonPanel panel = new ToggleButtonPanel();
    GridDialogLayoutData data = new GridDialogLayoutData(GridDialogLayoutData.FILL_VERTICAL);
    data.setGrabExcessHorizontalSpace(true);
    data.setVerticalSpan(2);
    component.add(panel.getContent(), data);
    return panel;
  }

  public JComponent getContent() {
    return component;
  }

  public void requestFocus() {
    //nothing to do
  }

  public void dispose() {
    //nothing to do
  }

  public IListObjectSelectionView<ITemplateTypeAggregation> addObjectSelectionList() {
    ListObjectSelectionView<ITemplateTypeAggregation> view = new ListObjectSelectionView<ITemplateTypeAggregation>(
        ITemplateTypeAggregation.class);
    GridDialogLayoutData data = new GridDialogLayoutData(GridDialogLayoutData.FILL_BOTH);
    data.setVerticalAlignment(GridAlignment.BEGINNING);
    component.add(new JScrollPane(view.getContent()), data);
    return view;
  }

  public ObjectSelectionView<IExaltedRuleSet> addRulesetSelectionView(
      String label,
      ListCellRenderer renderer,
      IExaltedRuleSet[] objects) {
    ObjectSelectionView<IExaltedRuleSet> view = new ObjectSelectionView<IExaltedRuleSet>(
        label,
        renderer,
        objects,
        false);
    JPanel panel = new JPanel(new GridDialogLayout(2, false));
    view.addTo(panel, GridDialogLayoutData.FILL_HORIZONTAL);
    component.add(panel, GridDialogLayoutData.FILL_HORIZONTAL);
    return view;
  }
}