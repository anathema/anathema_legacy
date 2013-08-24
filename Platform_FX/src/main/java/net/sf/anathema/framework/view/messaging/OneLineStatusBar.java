package net.sf.anathema.framework.view.messaging;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import net.sf.anathema.lib.gui.message.MessageTypeImagePaths;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.platform.tool.ImageContainer;
import net.sf.anathema.platform.tool.LoadImage;
import org.tbee.javafx.scene.layout.MigPane;

import java.awt.BorderLayout;

public class OneLineStatusBar implements StatusBar {

  private final Label label = new Label();
  private final MigPane panel = new MigPane();

  public OneLineStatusBar() {
    panel.add(label, BorderLayout.CENTER);
    label.setPrefSize(350, 20);
  }

  public Node getComponent() {
    return panel;
  }

  @Override
  public void setLatestMessage(IBasicMessage message) {
    ImageView imageView = new ImageView();
    label.setGraphic(imageView);
    LoadImage image = new LoadImage(new MessageTypeImagePaths().getIconPath(message.getType()));
    ImageContainer container = image.run();
    container.displayIn(imageView);
    label.setText(message.getText());
  }
}