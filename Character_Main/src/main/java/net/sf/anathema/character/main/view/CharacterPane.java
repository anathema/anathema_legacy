package net.sf.anathema.character.main.view;

import net.sf.anathema.hero.display.MultipleContentView;
import net.sf.anathema.framework.swing.IView;

public interface CharacterPane extends IView {

  MultipleContentView addMultipleContentView(String header);
}