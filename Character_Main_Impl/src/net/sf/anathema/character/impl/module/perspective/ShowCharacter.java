package net.sf.anathema.character.impl.module.perspective;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.PrintNameFile;

public class ShowCharacter implements EventHandler<ActionEvent> {
  private final PrintNameFile printNameFile;
  private final IAnathemaModel model;
  private final CharacterStackPresenter characterStack;

  public ShowCharacter(PrintNameFile printNameFile, IAnathemaModel model, CharacterStackPresenter characterStack) {
    this.printNameFile = printNameFile;
    this.model = model;
    this.characterStack = characterStack;
  }

  @Override
  public void handle(ActionEvent actionEvent) {
    characterStack.showCharacter(model, printNameFile.getRepositoryId());
  }
}
