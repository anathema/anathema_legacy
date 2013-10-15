package net.sf.anathema.hero.advance.overview.view;

import javafx.scene.Node;

//TODO (Swing->FX): Make this independent of JavaFX or move it to Character_Main_Fx
public interface OverviewDisplay {
  void setOverviewPane(Node node);
}