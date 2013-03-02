package net.sf.anathema.character.perspective;

import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.character.perspective.model.model.CharacterModel;
import net.sf.anathema.character.perspective.model.model.ItemSystemModel;
import net.sf.anathema.lib.resources.IStringResourceHandler;

public class CharacterGridPresenter {

  private final ItemSystemModel model;
  private final CharacterGridView view;
  private final Selector<CharacterIdentifier> selector;
  private final IStringResourceHandler resources;

  public CharacterGridPresenter(ItemSystemModel model, CharacterGridView view, Selector<CharacterIdentifier> selector,
                                IStringResourceHandler resources) {
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
    new CharacterButtonPresenter(resources, selector, character, view).initPresentation();
  }
}