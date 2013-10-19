package net.sf.anathema.character.main.view;

import net.sf.anathema.hero.advance.overview.view.OverviewContainer;
import net.sf.anathema.platform.fx.NodeHolder;

//TODO (Swing->FX): Move to Character_Main_FX
public interface CharacterView extends NodeHolder, OverviewContainer {

  SectionView addSection(String title);
}