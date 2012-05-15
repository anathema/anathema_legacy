package net.sf.anathema.character.impl.view;

import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.lib.gui.IView;

public interface CharacterPane extends IView {

  MultipleContentView addMultipleContentView(String header);
}
