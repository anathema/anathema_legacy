package net.sf.anathema.fx.hero.traitview;

import javafx.scene.Node;
import net.miginfocom.layout.CC;

public interface TraitViewPanel {
  void remove(Node panel);

  void add(Node panel);

  void add(Node panel, CC constraints);
}
