package net.sf.anathema.character.main.view;

import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.hero.advance.overview.view.OverviewContainer;

public interface CharacterView extends IView, OverviewContainer {

  SectionView addSection(String title);
}