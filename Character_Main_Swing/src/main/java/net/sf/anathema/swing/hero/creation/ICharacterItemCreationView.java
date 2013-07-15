package net.sf.anathema.swing.hero.creation;

import net.sf.anathema.character.main.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;

public interface ICharacterItemCreationView extends IPageContent {

  IToggleButtonPanel addToggleButtonPanel();

  VetoableObjectSelectionView<ITemplateTypeAggregation> addObjectSelectionList();
}