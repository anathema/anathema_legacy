package net.sf.anathema.character.impl.module.perspective;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.PrintNameFile;

public class ShowCharacter implements EventHandler<ActionEvent> {
  private final PrintNameFile printNameFile;
  private final CharacterStackPresenter characterStack;

  public ShowCharacter(PrintNameFile printNameFile, CharacterStackPresenter characterStack) {
    this.printNameFile = printNameFile;
    this.characterStack = characterStack;
  }

  @Override
  public void handle(ActionEvent actionEvent) {
    characterStack.showCharacter(printNameFile.getRepositoryId());
  }
}
