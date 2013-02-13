package net.sf.anathema.character.perspective;

import com.google.common.collect.Collections2;
import net.sf.anathema.character.CharacterPrintNameFileScanner;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.character.perspective.model.model.ItemSystemModel;
import net.sf.anathema.framework.view.PrintNameFile;

import java.util.Collection;

public class CharacterGridPresenter {

  private final ItemSystemModel model;
  private final CharacterGridView view;
  private final Selector<CharacterIdentifier> characterSelector;
  private final ToCharacterButtonDto characterTransformer;

  public CharacterGridPresenter(ItemSystemModel model, CharacterGridView view, CharacterStackPresenter stackPresenter, CharacterPrintNameFileScanner fileScanner) {
    this.model = model;
    this.view = view;
    this.characterSelector = new ShowOnSelect(stackPresenter);
    this.characterTransformer = new ToCharacterButtonDto(fileScanner);
  }

  public void initPresentation() {
    Collection<PrintNameFile> printNameFiles = model.collectCharacterPrintNameFiles();
    Collection<CharacterButtonDto> dtoList = Collections2.transform(printNameFiles, characterTransformer);
    view.addButtons(dtoList, characterSelector);
  }
}