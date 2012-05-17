package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.magic.IContentPresenter;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.view.IGroupedFavorableTraitViewFactory;
import net.sf.anathema.framework.presenter.view.ContentView;
import net.sf.anathema.framework.presenter.view.SimpleViewContentView;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.resources.IResources;

public class AttributesPresenter implements IContentPresenter {

  private final IGroupedFavorableTraitConfigurationView attributeView;
  private final FavorableTraitConfigurationPresenter presenter;
  private final String contentHeader;

  public AttributesPresenter(ICharacter character, IResources resources, IGroupedFavorableTraitViewFactory factory) {
    IIdentifiedTraitTypeGroup[] traitTypeGroups = character.getTraitConfiguration().getAttributeTypeGroups();
    this.attributeView = factory.createView(1);
    this.presenter = new FavorableTraitConfigurationPresenter(traitTypeGroups, character, attributeView, resources);
    this.contentHeader = resources.getString("CardView.AttributeConfiguration.Title"); //$NON-NLS-1$
  }

  @Override
  public ContentView getTabContent() {
    return new SimpleViewContentView(new ContentProperties(contentHeader).needsScrollBar(), attributeView);
  }

  @Override
  public void initPresentation() {
    presenter.init("AttributeGroupType.Name"); //$NON-NLS-1$
  }
}
