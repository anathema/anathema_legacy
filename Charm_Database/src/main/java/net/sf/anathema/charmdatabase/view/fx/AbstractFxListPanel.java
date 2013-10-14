package net.sf.anathema.charmdatabase.view.fx;

import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.platform.fx.ListSelectionView;
import org.tbee.javafx.scene.layout.MigPane;

public abstract class AbstractFxListPanel<T> {

  protected final MigPane pane = new MigPane(new LC().height("100"), new AC(), new AC().index(1).shrinkPrio(200));
  private ListSelectionView<T> listView = new ListSelectionView<>();

  protected AbstractFxListPanel(IconlessCellRenderer<T> renderer) {
    pane.add(listView.getNode(), new CC());
    listView.setCellRenderer(renderer);
  }

  protected void setObjects(T[] objects) {
    listView.setObjects(objects);
  }

  public final Node getNode() {
    return pane;
  }
}
