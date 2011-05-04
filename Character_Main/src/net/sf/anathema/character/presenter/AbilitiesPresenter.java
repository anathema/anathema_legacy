package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.presenter.charm.IContentPresenter;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.view.IGroupedFavorableTraitViewFactory;
import net.sf.anathema.framework.presenter.view.IViewContent;
import net.sf.anathema.framework.presenter.view.SimpleViewContent;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.resources.IResources;

public class AbilitiesPresenter implements IContentPresenter {

  private final IGroupedFavorableTraitConfigurationView abilityView;
  private final FavorableTraitConfigurationPresenter presenter;
  private final String contentHeader;

  public AbilitiesPresenter(
      ICharacterStatistics statistics,
      IResources resources,
      IGroupedFavorableTraitViewFactory factory) {
    IIdentifiedTraitTypeGroup[] traitTypeGroups = statistics.getTraitConfiguration().getAbilityTypeGroups();
    int columnCount = 2;
    this.abilityView = factory.createView(columnCount);
    this.presenter = new FavorableTraitConfigurationPresenter(traitTypeGroups, statistics, abilityView, resources);
    this.contentHeader = resources.getString("CardView.AbilityConfiguration.Title"); //$NON-NLS-1$
  }

  public IViewContent getTabContent() {
    return new SimpleViewContent(new ContentProperties(contentHeader).needsScrollbar(), abilityView);
  }

  public void initPresentation() {
    presenter.init("AbilityGroup"); //$NON-NLS-1$
  }
}
