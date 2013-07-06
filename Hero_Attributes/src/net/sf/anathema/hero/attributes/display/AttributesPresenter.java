package net.sf.anathema.hero.attributes.display;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.library.trait.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.hero.attributes.model.AttributesModelFetcher;
import net.sf.anathema.character.view.ColumnCount;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.display.FavorableTraitConfigurationPresenter;
import net.sf.anathema.lib.resources.Resources;

public class AttributesPresenter {

  private final FavorableTraitConfigurationPresenter presenter;

  public AttributesPresenter(Hero hero, Resources resources, GroupedFavorableTraitConfigurationView view) {
    IIdentifiedTraitTypeGroup[] traitTypeGroups = AttributesModelFetcher.fetch(hero).getAttributeTypeGroups();
    view.initGui(new ColumnCount(1), hero.getTemplate().getTemplateType().getCharacterType());
    this.presenter = new FavorableTraitConfigurationPresenter(traitTypeGroups, hero, view, resources);
  }

  public void initPresentation() {
    presenter.init("AttributeGroupType.Name");
  }
}
