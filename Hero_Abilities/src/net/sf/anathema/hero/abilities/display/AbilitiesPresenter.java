package net.sf.anathema.hero.abilities.display;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.main.model.abilities.AbilityModelFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.FavorableTraitConfigurationPresenter;
import net.sf.anathema.character.view.ColumnCount;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.lib.resources.Resources;

public class AbilitiesPresenter {

  private final FavorableTraitConfigurationPresenter presenter;

  public AbilitiesPresenter(ICharacter character, Resources resources, IGroupedFavorableTraitConfigurationView view) {
    IIdentifiedTraitTypeGroup[] traitTypeGroups = AbilityModelFetcher.fetch(character).getAbilityTypeGroups();
    view.initGui(new ColumnCount(2));
    this.presenter = new FavorableTraitConfigurationPresenter(traitTypeGroups, character, view, resources);
  }

  public void initPresentation() {
    presenter.init("AbilityGroup");
  }
}