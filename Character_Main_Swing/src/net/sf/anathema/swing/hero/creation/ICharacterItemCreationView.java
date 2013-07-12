package net.sf.anathema.swing.hero.creation;

import net.sf.anathema.character.main.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.selection.IVetoableObjectSelectionView;

public interface ICharacterItemCreationView extends IPageContent {

  IToggleButtonPanel addToggleButtonPanel();

  IVetoableObjectSelectionView<ITemplateTypeAggregation> addObjectSelectionList();
}