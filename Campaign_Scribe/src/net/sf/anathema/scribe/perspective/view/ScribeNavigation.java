package net.sf.anathema.scribe.perspective.view;

import javafx.application.Platform;
import javafx.scene.control.Button;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.platform.fx.Navigation;
import net.sf.anathema.platform.tool.Execute;
import net.sf.anathema.scribe.scroll.persistence.ScrollReference;

public class ScribeNavigation extends Navigation {

  public void addScroll(final ScrollReference reference, final Command command) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        Button button = new Button(reference.printName);
        button.getStyleClass().add("scribe-navigation-button");
        button.setOnAction(new Execute(command));
        addElementToNavigation(button);
      }
    });
  }
}