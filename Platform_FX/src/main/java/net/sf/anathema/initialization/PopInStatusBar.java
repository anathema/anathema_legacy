package net.sf.anathema.initialization;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import net.sf.anathema.framework.view.messaging.StatusBar;
import net.sf.anathema.lib.message.IBasicMessage;
import org.controlsfx.control.NotificationPane;

import static javafx.util.Duration.millis;

public class PopInStatusBar implements StatusBar {
  private NotificationPane pane;

  public PopInStatusBar(NotificationPane pane) {
    this.pane = pane;
    pane.setShowFromTop(false);
  }

  @Override
  public void setLatestMessage(IBasicMessage message) {
    pane.show(message.getText());
    new Timeline(new KeyFrame(millis(2500), event -> pane.hide())).play();
  }
}