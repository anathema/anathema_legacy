package net.sf.anathema.hero.equipment.display.view;

import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentItemRenderer;
import net.sf.anathema.hero.equipment.display.presenter.RelativePathWithDisabling;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.platform.fx.UITableCell;
import org.tbee.javafx.scene.layout.MigPane;

public class EquipmentCell extends ListCell<IEquipmentItem> {
  public static final String OWNED_EQUIPMENT = "ownedEquipment";
  private EquipmentItemRenderer renderer;

  public EquipmentCell(EquipmentItemRenderer renderer) {
    this.renderer = renderer;
    getStyleClass().add(OWNED_EQUIPMENT);
  }

  @Override
  public void updateItem(IEquipmentItem item, boolean empty) {
    super.updateItem(item, empty);
    if (item == null) {
      setText("");
      setGraphic(null);
    } else {
      setText(renderer.getLabel(item));
      Node combinedGraphic = createGraphic(item);
      setGraphic(combinedGraphic);
    }
  }

  private Node createGraphic(IEquipmentItem item) {
    MigPane pane = new MigPane(LayoutUtils.withoutInsets());
    for (RelativePathWithDisabling pathWithDisabling : renderer.getIcons(item)) {
      Image image = UITableCell.loadImage(pathWithDisabling.path);
      ImageView imageView = new ImageView(image);
      if (!pathWithDisabling.enabled) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(-1.0);
        imageView.setEffect(colorAdjust);
      }
      pane.add(imageView);
    }
    return pane;
  }
}