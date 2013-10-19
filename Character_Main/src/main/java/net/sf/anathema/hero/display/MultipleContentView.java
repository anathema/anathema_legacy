package net.sf.anathema.hero.display;

import net.sf.anathema.platform.fx.NodeHolder;

//TODO (Swing->FX): Move to Character_Main_FX
public interface MultipleContentView {

  void addView(NodeHolder view, ContentProperties tabProperties);

  void finishInitialization();
}