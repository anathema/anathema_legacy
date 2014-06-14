package net.sf.anathema.initialization;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import net.sf.anathema.framework.view.messaging.MessageTypeImagePaths;
import net.sf.anathema.framework.view.messaging.StatusBar;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.platform.tool.ImageContainer;
import net.sf.anathema.platform.tool.LoadImage;
import org.controlsfx.control.NotificationPane;

import static javafx.util.Duration.millis;
import static net.sf.anathema.lib.message.MessageDuration.Temporary;

public class PopInStatusBar implements StatusBar {
  private NotificationPane pane;

  public PopInStatusBar(NotificationPane pane) {
    this.pane = pane;
    pane.setShowFromTop(false);
  }

  @Override
  public void setLatestMessage(Message message) {
    ImageView imageView = new ImageView();
    pane.setGraphic(imageView);
    pane.setText(message.getText());
    LoadImage image = new LoadImage(new MessageTypeImagePaths().getIconPath(message.getType()));
    ImageContainer container = image.run();
    container.displayIn(imageView);
    pane.show();
    if (message.getDuration() == Temporary) {
      new Timeline(new KeyFrame(millis(2500), event -> pane.hide())).play();
    }
  }
}