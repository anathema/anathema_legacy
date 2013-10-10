package net.sf.anathema.hero.creation;

import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;

public interface CharacterCreationView  {

  ToggleButtonPanel addToggleButtonPanel();

  VetoableObjectSelectionView<HeroTemplate> addObjectSelectionList();
}