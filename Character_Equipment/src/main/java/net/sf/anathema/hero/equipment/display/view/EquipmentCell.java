package net.sf.anathema.hero.equipment.display.view;

import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentItemRenderer;
import net.sf.anathema.hero.equipment.display.presenter.RelativePathWithDisabling;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.platform.fx.UITableCell;
import org.tbee.javafx.scene.layout.MigPane;

public class EquipmentCell extends ListCell<IEquipmentItem> {
  private static final String OWNED_EQUIPMENT = "ownedEquipment";
  private final ItemChangeListener listener = new ItemChangeListener();
  private EquipmentItemRenderer renderer;
  private IEquipmentItem currentItem;

  public EquipmentCell(EquipmentItemRenderer renderer) {
    this.renderer = renderer;
    getStyleClass().add(OWNED_EQUIPMENT);
  }

  @Override
  public void updateItem(IEquipmentItem item, boolean empty) {
    super.updateItem(item, empty);
    if (item == null) {
      clearCell();
    } else {
      showItem(item);
    }
  }

  private void clearCell() {
    if (currentItem != null) {
      currentItem.removeChangeListener(listener);
    }
    currentItem = null;
    setText("");
    setGraphic(null);
  }

  private void showItem(IEquipmentItem item) {
    item.addChangeListener(listener);
    currentItem = item;
    displayItem(item);
  }

  private void displayItem(IEquipmentItem item) {
    setText(renderer.getLabel(item));
    Node combinedGraphic = createGraphic(item);
    setGraphic(combinedGraphic);
  }

  private Node createGraphic(IEquipmentItem item) {
    MigPane pane = new MigPane(LayoutUtils.withoutInsets());
    for (RelativePathWithDisabling pathWithDisabling : renderer.getIcons(item)) {
      ImageView imageView = createImage(pathWithDisabling);
      pane.add(imageView);
    }
    return pane;
  }

  private ImageView createImage(RelativePathWithDisabling pathWithDisabling) {
    Image image = UITableCell.loadImage(pathWithDisabling.path);
    ImageView imageView = new ImageView(image);
    if (!pathWithDisabling.enabled) {
      removeColor(imageView);
    }
    return imageView;
  }

  private void removeColor(ImageView imageView) {
    ColorAdjust colorAdjust = new ColorAdjust();
    colorAdjust.setSaturation(-1.0);
    imageView.setEffect(colorAdjust);
  }

  private class ItemChangeListener implements ChangeListener {
    @Override
    public void changeOccurred() {
      displayItem(currentItem);
    }
  }
}