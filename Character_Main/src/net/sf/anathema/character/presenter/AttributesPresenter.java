package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.character.main.model.attributes.AttributeModelFetcher;
import net.sf.anathema.character.view.ColumnCount;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.lib.resources.Resources;

public class AttributesPresenter {

  private final FavorableTraitConfigurationPresenter presenter;

  public AttributesPresenter(Hero hero, Resources resources, IGroupedFavorableTraitConfigurationView view) {
    IIdentifiedTraitTypeGroup[] traitTypeGroups = AttributeModelFetcher.fetch(hero).getAttributeTypeGroups();
    view.initGui(new ColumnCount(1));
    this.presenter = new FavorableTraitConfigurationPresenter(traitTypeGroups, hero, view, resources);
  }

  public void initPresentation() {
    presenter.init("AttributeGroupType.Name");
  }
}
