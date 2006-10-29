package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.presenter.charm.IContentPresenter;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.view.IGroupedFavorableTraitViewFactory;
import net.sf.anathema.framework.presenter.view.IViewContent;
import net.sf.anathema.framework.presenter.view.SimpleViewContent;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public class AttributesPresenter implements IContentPresenter {

  private final IGroupedFavorableTraitConfigurationView attributeView;
  private final FavorableTraitConfigurationPresenter presenter;
  private final String contentHeader;

  public AttributesPresenter(
      ICharacterStatistics statistics,
      IResources resources,
      IGroupedFavorableTraitViewFactory factory) {
    IIdentifiedTraitTypeGroup[] traitTypeGroups = statistics.getTraitConfiguration().getAttributeTypeGroups();
    this.attributeView = factory.createView(1);
    this.presenter = new FavorableTraitConfigurationPresenter(traitTypeGroups, statistics, attributeView, resources);
    this.contentHeader = resources.getString("CardView.AttributeConfiguration.Title"); //$NON-NLS-1$
  }

  public IViewContent getTabContent() {
    return new SimpleViewContent(new ContentProperties(contentHeader).needsScrollbar(), (IView) attributeView);
  }

  public void initPresentation() {
    presenter.init("AttributeGroupType.Name"); //$NON-NLS-1$
  }
}