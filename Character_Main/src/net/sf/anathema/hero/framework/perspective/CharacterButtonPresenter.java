package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;
import net.sf.anathema.hero.framework.perspective.model.CharacterItemModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.resources.Resources;

public class CharacterButtonPresenter {

  private Resources resources;
  private Selector<CharacterIdentifier> selector;
  private CharacterItemModel character;
  private CharacterGridView view;

  public CharacterButtonPresenter(Resources resources, Selector<CharacterIdentifier> selector, CharacterItemModel character, CharacterGridView view) {
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
    character.whenFeaturesChange(new ChangeListener() {
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
