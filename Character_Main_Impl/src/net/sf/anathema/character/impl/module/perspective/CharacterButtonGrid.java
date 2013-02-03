package net.sf.anathema.character.impl.module.perspective;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import net.sf.anathema.character.perspective.CharacterStackPresenter;
import net.sf.anathema.framework.IAnathemaModel;

import javax.swing.JComponent;

public class CharacterButtonGrid {

  private final JFXPanel panel = new JFXPanel();

  public void fillFromRepository(final IAnathemaModel model, final CharacterStackPresenter characterStack) {
    Platform.runLater(new InitCharacterButtons(model, characterStack, panel));
  }

  public JComponent getComponent() {
    return panel;
  }
}
