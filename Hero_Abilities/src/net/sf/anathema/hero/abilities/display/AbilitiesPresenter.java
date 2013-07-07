package net.sf.anathema.hero.abilities.display;

import net.sf.anathema.character.main.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.main.library.trait.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.hero.abilities.AbilityModelFetcher;
import net.sf.anathema.character.main.view.ColumnCount;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.display.FavorableTraitConfigurationPresenter;
import net.sf.anathema.lib.resources.Resources;

public class AbilitiesPresenter {

  private final FavorableTraitConfigurationPresenter presenter;

  public AbilitiesPresenter(Hero hero, Resources resources, GroupedFavorableTraitConfigurationView view) {
    IIdentifiedTraitTypeGroup[] traitTypeGroups = AbilityModelFetcher.fetch(hero).getAbilityTypeGroups();
    view.initGui(new ColumnCount(2), hero.getTemplate().getTemplateType().getCharacterType());
    this.presenter = new FavorableTraitConfigurationPresenter(traitTypeGroups, hero, view, resources);
  }

  public void initPresentation() {
    presenter.init("AbilityGroup");
  }
}