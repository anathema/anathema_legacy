package net.sf.anathema.character.perspective;

import net.sf.anathema.character.perspective.model.CharacterIdentifier;
import net.sf.anathema.character.perspective.model.CharacterModel;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.resources.Resources;

public class CharacterButtonPresenter {

  private Resources resources;
  private Selector<CharacterIdentifier> selector;
  private CharacterModel character;
  private CharacterGridView view;

  public CharacterButtonPresenter(Resources resources, Selector<CharacterIdentifier> selector, CharacterModel character, CharacterGridView view) {
    this.resources = resources;
    this.selector = selector;
    this.character = character;
    this.view = view;
  }

  public void initPresentation() {
    CharacterButtonDto dto = extractButtonDto();
    view.addButton(dto, selector);
    initDescriptiveFeatureListening();
  }

  private void initDescriptiveFeatureListening() {
    character.whenFeaturesChange(new IChangeListener() {
      @Override
      public void changeOccurred() {
        view.updateButton(extractButtonDto());
      }
    });
  }

  private CharacterButtonDto extractButtonDto() {
    return new ToCharacterButtonDto(resources).apply(character.getDescriptiveFeatures());
  }
}
