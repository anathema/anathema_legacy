package net.sf.anathema.swing.hero.creation;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.main.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.framework.swing.selection.ListObjectSelectionView;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CharacterItemCreationView implements ICharacterItemCreationView {

  private final JPanel component;

  public CharacterItemCreationView() {
    this.component = new JPanel(new MigLayout(LayoutUtils.withoutInsets().gridGapX("10")));
  }

  @Override
  public IToggleButtonPanel addToggleButtonPanel() {
    ToggleButtonPanel panel = new ToggleButtonPanel();
    component.add(panel.getComponent(), new CC().grow().pushY());
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
  public IListObjectSelectionView<ITemplateTypeAggregation> addObjectSelectionList() {
    ListObjectSelectionView<ITemplateTypeAggregation> view = new ListObjectSelectionView<>(ITemplateTypeAggregation.class);
    JScrollPane scrollPane = new JScrollPane(view.getComponent());
    component.add(scrollPane, new CC().grow().push());
    return view;
  }
}