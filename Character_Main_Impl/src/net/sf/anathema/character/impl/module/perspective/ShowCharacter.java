package net.sf.anathema.character.impl.module.perspective;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import net.sf.anathema.framework.view.PrintNameFile;

public class ShowCharacter implements EventHandler<ActionEvent> {
  private final PrintNameFile printNameFile;
  private final CharacterStackPresenter presenter;

  public ShowCharacter(PrintNameFile printNameFile, CharacterStackPresenter characterStack) {
    this.printNameFile = printNameFile;
    this.presenter = characterStack;
  }

  @Override
  public void handle(ActionEvent actionEvent) {
    String identifier = printNameFile.getRepositoryId();
    presenter.showCharacter(identifier);
  }
}
