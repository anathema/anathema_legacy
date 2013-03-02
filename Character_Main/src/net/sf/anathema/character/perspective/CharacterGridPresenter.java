package net.sf.anathema.character.perspective;

import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.character.perspective.model.model.CharacterModel;
import net.sf.anathema.character.perspective.model.model.ItemSystemModel;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.resources.IStringResourceHandler;

public class CharacterGridPresenter {

  private final ItemSystemModel model;
  private final CharacterGridView view;
  private final Selector<CharacterIdentifier> characterSelector;
  private final ToCharacterButtonDto characterTransformer;

  public CharacterGridPresenter(ItemSystemModel model, CharacterGridView view, Selector<CharacterIdentifier> characterSelector,
                                IStringResourceHandler resources) {
    this.model = model;
    this.view = view;
    this.characterSelector = characterSelector;
    this.characterTransformer = new ToCharacterButtonDto(resources);
  }

  public void initPresentation() {
    for (final CharacterModel character : model.collectAllExistingCharacters()) {
      final CharacterButtonDto dto = characterTransformer.apply(character.getDescriptiveFeatures());
      character.whenFeaturesChange(new IChangeListener() {
        @Override
        public void changeOccurred() {
          view.setName(dto.identifier, character.getDescriptiveFeatures().getPrintName());
        }
      });
      view.addButton(dto, characterSelector);
    }
  }
}