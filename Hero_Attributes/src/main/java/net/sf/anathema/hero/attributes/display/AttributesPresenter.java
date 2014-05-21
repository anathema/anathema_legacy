package net.sf.anathema.hero.attributes.display;

import net.sf.anathema.hero.traits.display.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeList;
import net.sf.anathema.character.framework.display.ColumnCount;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.attributes.model.AttributesModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.display.FavorableTraitConfigurationPresenter;

public class AttributesPresenter {

  private final FavorableTraitConfigurationPresenter presenter;

  public AttributesPresenter(Hero hero, Resources resources, GroupedFavorableTraitConfigurationView view) {
    IdentifiedTraitTypeList[] traitTypeGroups = AttributesModelFetcher.fetch(hero).getTraitTypeList();
    view.initGui(new ColumnCount(1));
    this.presenter = new FavorableTraitConfigurationPresenter(traitTypeGroups, hero, view, resources);
  }

  public void initPresentation() {
    presenter.init("AttributeGroupType.Name");
  }
}
