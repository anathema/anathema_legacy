package net.sf.anathema.character.perspective;

import net.sf.anathema.character.perspective.model.CharacterIdentifier;
import net.sf.anathema.character.perspective.model.CharacterItemModel;
import net.sf.anathema.character.perspective.model.ItemSystemModel;
import net.sf.anathema.lib.resources.Resources;

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
    for (final CharacterItemModel character : model.collectAllExistingCharacters()) {
      initPresentation(character);
    }
  }

  private void initPresentation(final CharacterItemModel character) {
    new CharacterButtonPresenter(resources, selector, character, view).initPresentation();
  }
}