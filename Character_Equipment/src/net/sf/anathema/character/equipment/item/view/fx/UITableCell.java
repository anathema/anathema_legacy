package net.sf.anathema.character.equipment.item.view.fx;

import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;
import net.sf.anathema.platform.fx.ResourceLoader;

import java.io.InputStream;

public class UITableCell<T> extends ListCell<T> {
  private TechnologyAgnosticUIConfiguration<T> configuration;

  public UITableCell(TechnologyAgnosticUIConfiguration<T> configuration) {
    this.configuration = configuration;
  }

  @Override
  public void updateItem(T item, boolean empty) {
    super.updateItem(item, empty);
    if (item == null) {
      return;
    }
    setText(configuration.getLabel(item));                                                                   
    setTooltip(new Tooltip(configuration.getToolTipText(item)));
    Image image = loadImageForItem(item);
    setGraphic(new ImageView(image));
  }

  private Image loadImageForItem(T item) {
    RelativePath relativePath = configuration.getIconsRelativePath(item);
    ResourceLoader resourceLoader = new ResourceLoader();
    InputStream imageStream = resourceLoader.loadResource(relativePath);
    return new Image(imageStream, 16, 16, true, true);
  }
}
