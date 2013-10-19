package net.sf.anathema.hero.display;

import net.sf.anathema.platform.fx.NodeHolder;

public interface MultipleContentView {

  void addView(NodeHolder view, ContentProperties tabProperties);

  void finishInitialization();
}