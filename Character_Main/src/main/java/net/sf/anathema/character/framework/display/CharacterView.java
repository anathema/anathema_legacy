package net.sf.anathema.character.framework.display;

import net.sf.anathema.hero.advance.overview.view.OverviewContainer;

public interface CharacterView extends OverviewContainer {

  SectionView addSection(String title);
}