package net.sf.anathema.fx.hero.perspective;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import net.sf.anathema.hero.framework.perspective.Selector;
import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;

public class CharacterSelected implements EventHandler<ActionEvent> {
  private final Selector<CharacterIdentifier> characterSelector;
  private final CharacterIdentifier identifier;

  public CharacterSelected(Selector<CharacterIdentifier> characterSelector, CharacterIdentifier identifier) {
    this.characterSelector = characterSelector;
    this.identifier = identifier;
  }

  @Override
  public void handle(ActionEvent actionEvent) {
    characterSelector.selected(identifier);
  }
}
