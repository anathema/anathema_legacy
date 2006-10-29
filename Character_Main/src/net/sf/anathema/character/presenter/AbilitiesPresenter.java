package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.presenter.charm.IContentPresenter;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.view.IGroupedFavorableTraitViewFactory;
import net.sf.anathema.framework.presenter.view.ITabContent;
import net.sf.anathema.framework.presenter.view.SimpleViewTabContent;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.gui.IView;
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
    int columnCount = traitTypeGroups.length / 2 + 1;
    this.abilityView = factory.createView(columnCount);
    this.presenter = new FavorableTraitConfigurationPresenter(traitTypeGroups, statistics, abilityView, resources);
    this.contentHeader = resources.getString("CardView.AbilityConfiguration.Title"); //$NON-NLS-1$
  }

  public ITabContent getTabContent() {
    return new SimpleViewTabContent(new ContentProperties(contentHeader).needsScrollbar(), (IView) abilityView);
  }

  public void initPresentation() {
    presenter.init("AbilityGroup"); //$NON-NLS-1$
  }
}
