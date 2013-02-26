package net.sf.anathema.character.perspective;

import com.google.common.collect.Collections2;
import net.sf.anathema.character.CharacterPrintNameFileScanner;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.character.perspective.model.model.ItemSystemModel;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.resources.IStringResourceHandler;

import java.util.Collection;

public class CharacterGridPresenter {

  private final ItemSystemModel model;
  private final CharacterGridView view;
  private final Selector<CharacterIdentifier> characterSelector;
  private final ToCharacterButtonDto characterTransformer;

  public CharacterGridPresenter(ItemSystemModel model, CharacterGridView view, Selector<CharacterIdentifier> characterSelector, CharacterPrintNameFileScanner fileScanner, IStringResourceHandler resources) {
    this.model = model;
    this.view = view;
    this.characterSelector = characterSelector;
    this.characterTransformer = new ToCharacterButtonDto(fileScanner, resources);
  }

  public void initPresentation() {
    Collection<PrintNameFile> printNameFiles = model.collectAllCharacters();
    Collection<CharacterButtonDto> dtoList = Collections2.transform(printNameFiles, characterTransformer);
    view.addButtons(dtoList, characterSelector);
  }
}