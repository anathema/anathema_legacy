package net.sf.anathema.character.impl.module.repository;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.impl.module.IToggleButtonPanel;
import net.sf.anathema.character.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;

public interface ICharacterItemCreationView extends IPageContent {

  IToggleButtonPanel addToggleButtonPanel();

  IListObjectSelectionView<ITemplateTypeAggregation> addObjectSelectionList();
}