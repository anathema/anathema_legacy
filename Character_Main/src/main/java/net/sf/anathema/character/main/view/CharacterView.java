package net.sf.anathema.character.main.view;

import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.hero.advance.overview.view.OverviewContainer;
import net.sf.anathema.platform.fx.NodeHolder;

public interface CharacterView extends NodeHolder, OverviewContainer {

  SectionView addSection(String title);
}