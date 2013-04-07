package net.sf.anathema.character.impl.view;

import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.framework.swing.IView;

import javax.swing.JComponent;

public interface CharacterPane extends IView {

  MultipleContentView addMultipleContentView(String header);

  void setOverview(JComponent component);
}
