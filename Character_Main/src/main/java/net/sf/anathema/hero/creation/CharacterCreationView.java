package net.sf.anathema.hero.creation;

import net.sf.anathema.hero.template.HeroTemplate;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;

public interface CharacterCreationView {

  ToggleButtonPanel addToggleButtonPanel();

  VetoableObjectSelectionView<HeroTemplate> addObjectSelectionList();

  void show();

  void close();

  Tool addButton();

  void setTitle(String title);
}