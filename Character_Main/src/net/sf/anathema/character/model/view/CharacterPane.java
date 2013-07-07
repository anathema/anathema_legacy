package net.sf.anathema.character.model.view;

import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.framework.swing.IView;

public interface CharacterPane extends IView {

  MultipleContentView addMultipleContentView(String header);
}