package net.sf.anathema.character.platform.module.repository;

import net.sf.anathema.character.platform.module.IToggleButtonPanel;
import net.sf.anathema.character.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;

public interface ICharacterItemCreationView extends IPageContent {

  IToggleButtonPanel addToggleButtonPanel();

  IListObjectSelectionView<ITemplateTypeAggregation> addObjectSelectionList();
}