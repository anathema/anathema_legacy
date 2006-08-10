package net.sf.anathema.character.impl.module.repository;

import javax.swing.ListCellRenderer;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.impl.module.IToggleButtonPanel;
import net.sf.anathema.character.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;

public interface ICharacterItemCreationView extends IPageContent {

  public IToggleButtonPanel addToggleButtonPanel();

  public IListObjectSelectionView<ITemplateTypeAggregation> addObjectSelectionList();

  public ObjectSelectionView<IExaltedRuleSet> addRulesetSelectionView(
      String label,
      ListCellRenderer renderer,
      IExaltedRuleSet[] objects);
}
