package net.sf.anathema.character.main.library.trait.view.fx;

import javafx.scene.Node;
import net.miginfocom.layout.CC;

public interface TraitViewPanel {
  void remove(Node panel);

  void add(Node panel);

  void add(Node panel, CC constraints);
}
