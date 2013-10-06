package net.sf.anathema.swing.hero.creation;

import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;

public interface CharacterCreationView extends IPageContent {

  ToggleButtonPanel addToggleButtonPanel();

  VetoableObjectSelectionView<HeroTemplate> addObjectSelectionList();
}