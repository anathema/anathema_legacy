package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.magic.IContentPresenter;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.view.IGroupedFavorableTraitViewFactory;
import net.sf.anathema.framework.presenter.view.ContentView;
import net.sf.anathema.framework.presenter.view.SimpleViewContentView;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.resources.Resources;

public class AbilitiesPresenter implements IContentPresenter {

  private final IGroupedFavorableTraitConfigurationView abilityView;
  private final FavorableTraitConfigurationPresenter presenter;
  private final String contentHeader;

  public AbilitiesPresenter(ICharacter character, Resources resources, IGroupedFavorableTraitViewFactory factory) {
    IIdentifiedTraitTypeGroup[] traitTypeGroups = character.getTraitConfiguration().getAbilityTypeGroups();
    int columnCount = 2;
    this.abilityView = factory.createView(columnCount);
    this.presenter = new FavorableTraitConfigurationPresenter(traitTypeGroups, character, abilityView, resources);
    this.contentHeader = resources.getString("CardView.AbilityConfiguration.Title");
  }

  @Override
  public ContentView getTabContent() {
    return new SimpleViewContentView(new ContentProperties(contentHeader), abilityView);
  }

  @Override
  public void initPresentation() {
    presenter.init("AbilityGroup");
  }
}
