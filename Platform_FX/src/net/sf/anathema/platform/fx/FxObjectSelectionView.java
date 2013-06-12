package net.sf.anathema.platform.fx;

import javafx.scene.Node;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;

public interface FxObjectSelectionView<V> extends IObjectSelectionView<V> {
  Node getNode();
}