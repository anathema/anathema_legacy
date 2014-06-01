package net.sf.anathema.hero.equipment.display.view;

import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentItemRenderer;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.platform.fx.UITableCell;
import org.tbee.javafx.scene.layout.MigPane;

public class EquipmentCell extends ListCell<IEquipmentItem> {
  private EquipmentItemRenderer renderer;

  public EquipmentCell(EquipmentItemRenderer renderer) {
    this.renderer = renderer;
  }

  @Override
  public void updateItem(IEquipmentItem item, boolean empty) {
    super.updateItem(item, empty);
    if (item == null){
      return;
    }
    setText(renderer.getLabel(item));
    Node combinedGraphic = createGraphic(item);
    setGraphic(combinedGraphic);
    getStyleClass().addAll("ownedEquipment");
  }

  private Node createGraphic(IEquipmentItem item) {
    MigPane pane = new MigPane(LayoutUtils.withoutInsets());
    for (RelativePath relativePath : renderer.getIcons(item)) {
      Image image = UITableCell.loadImage(relativePath);
      pane.add(new ImageView(image));
    }
    return pane;
  }
}
