package net.sf.anathema.character.model.view;

import net.sf.anathema.framework.swing.IView;

public interface CharacterView extends IView, OverviewContainer {

  SectionView addSection(String title);
}