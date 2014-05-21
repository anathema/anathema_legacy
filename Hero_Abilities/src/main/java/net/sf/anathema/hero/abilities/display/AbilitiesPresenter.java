package net.sf.anathema.hero.abilities.display;

import net.sf.anathema.character.main.library.trait.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.main.traits.lists.IdentifiedTraitTypeList;
import net.sf.anathema.character.main.view.ColumnCount;
import net.sf.anathema.hero.abilities.model.AbilityModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.display.FavorableTraitConfigurationPresenter;
import net.sf.anathema.framework.environment.Resources;

public class AbilitiesPresenter {

  private final FavorableTraitConfigurationPresenter presenter;

  public AbilitiesPresenter(Hero hero, Resources resources, GroupedFavorableTraitConfigurationView view) {
    IdentifiedTraitTypeList[] traitTypeGroups = AbilityModelFetcher.fetch(hero).getTraitTypeList();
    view.initGui(new ColumnCount(2));
    this.presenter = new FavorableTraitConfigurationPresenter(traitTypeGroups, hero, view, resources);
  }

  public void initPresentation() {
    presenter.init("AbilityGroup");
  }
}