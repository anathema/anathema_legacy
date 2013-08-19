package net.sf.anathema.hero.display;

import net.sf.anathema.framework.swing.IView;

public interface MultipleContentView {

  void addView(IView view, ContentProperties tabProperties);

  void finishInitialization();
}