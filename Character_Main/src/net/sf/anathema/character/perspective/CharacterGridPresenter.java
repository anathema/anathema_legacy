package net.sf.anathema.character.perspective;

import net.sf.anathema.character.perspective.model.CharacterIdentifier;
import net.sf.anathema.character.perspective.model.CharacterModel;
import net.sf.anathema.character.perspective.model.ItemSystemModel;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.Resources;

import java.text.MessageFormat;

public class CharacterGridPresenter {

  private final ItemSystemModel model;
  private final CharacterGridView view;
  private final Selector<CharacterIdentifier> selector;
  private final Resources resources;

  public CharacterGridPresenter(ItemSystemModel model, CharacterGridView view, Selector<CharacterIdentifier> selector, Resources resources) {
    this.model = model;
    this.view = view;
    this.selector = selector;
    this.resources = resources;
  }

  public void initPresentation() {
    for (final CharacterModel character : model.collectAllExistingCharacters()) {
      initPresentation(character);
    }
  }

  private void initPresentation(final CharacterModel character) {
    try {
      new CharacterButtonPresenter(resources, selector, character, view).initPresentation();
    } catch (Exception e) {
      String printName = character.getDescriptiveFeatures().getPrintName();
      Logger.getLogger(CharacterGridPresenter.class).error(MessageFormat.format("Could not initialize character ''{0}''", printName), e);
    }
  }
}