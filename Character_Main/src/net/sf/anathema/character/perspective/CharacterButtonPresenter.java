package net.sf.anathema.character.perspective;

import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.character.perspective.model.model.CharacterModel;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.resources.IStringResourceHandler;

public class CharacterButtonPresenter {

  private IStringResourceHandler resources;
  private Selector<CharacterIdentifier> selector;
  private CharacterModel character;
  private CharacterGridView view;

  public CharacterButtonPresenter(IStringResourceHandler resources, Selector<CharacterIdentifier> selector, CharacterModel character,
                                  CharacterGridView view) {
    this.resources = resources;
    this.selector = selector;
    this.character = character;
    this.view = view;
  }

  public void initPresentation() {
    final DescriptiveFeatures features = character.getDescriptiveFeatures();
    CharacterButtonDto dto = new ToCharacterButtonDto(resources).apply(features);
    view.addButton(dto, selector);
    character.whenFeaturesChange(new IChangeListener() {
      @Override
      public void changeOccurred() {
        view.setName(features.getIdentifier(), character.getDescriptiveFeatures().getPrintName());
      }
    });
  }
}
