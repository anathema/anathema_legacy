package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.gui.action.SmartAction;

import java.awt.Component;

public class ShowCharacterAction extends SmartAction {
  private final PrintNameFile printNameFile;
  private final IAnathemaModel model;
  private final CharacterStack characterStack;

  public ShowCharacterAction(PrintNameFile printNameFile, IAnathemaModel model, CharacterStack characterStack) {
    super(printNameFile.getPrintName());
    this.printNameFile = printNameFile;
    this.model = model;
    this.characterStack = characterStack;
  }

  @Override
  protected void execute(Component parentComponent) {
    characterStack.showCharacter(model, printNameFile.getRepositoryId());
  }
}
