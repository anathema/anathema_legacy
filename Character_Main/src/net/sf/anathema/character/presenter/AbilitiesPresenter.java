package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.lib.resources.Resources;

public class AbilitiesPresenter {

  private final FavorableTraitConfigurationPresenter presenter;

  public AbilitiesPresenter(ICharacter character, Resources resources, IGroupedFavorableTraitConfigurationView view) {
    IIdentifiedTraitTypeGroup[] traitTypeGroups = character.getTraitConfiguration().getAbilityTypeGroups();
    view.initGui(2);
    this.presenter = new FavorableTraitConfigurationPresenter(traitTypeGroups, character, view, resources);
  }

  public void initPresentation() {
    presenter.init("AbilityGroup");
  }
}