package net.sf.anathema.character.view;

import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.framework.swing.IView;

public interface CharacterView extends IView, OverviewContainer {

  MultipleContentView addMultipleContentView(String header);

  IMagicViewFactory createMagicViewFactory();

  SectionView addSection(String title);
}