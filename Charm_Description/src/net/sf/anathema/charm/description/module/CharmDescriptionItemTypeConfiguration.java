package net.sf.anathema.charm.description.module;

import net.sf.anathema.charm.description.view.CharmDescriptionView;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.initialization.ItemTypeConfiguration;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

@ItemTypeConfiguration
public final class CharmDescriptionItemTypeConfiguration extends AbstractItemTypeConfiguration {

  public static final String CHARM_DESCRIPTION_ITEM_TYPE_ID = "CharmDescription"; //$NON-NLS-1$
  private static final RepositoryConfiguration REPOSITORY_CONFIGURATION = new RepositoryConfiguration(".cdsc", "CharmDescription/");

  public CharmDescriptionItemTypeConfiguration() {
    super(new ItemType(CHARM_DESCRIPTION_ITEM_TYPE_ID, REPOSITORY_CONFIGURATION, false));
  }

  @Override
  public void initModel(IAnathemaModel model) {
    // nothing to do
  }

  @Override
  public void fillPresentationExtensionPoints(IRegistry<String, IAnathemaExtension> extensionPointRegistry, IResources resources, IAnathemaModel model, IAnathemaView view) {
    // nothing to do
  }

  @Override
  protected IItemViewFactory createItemViewFactory(final IAnathemaModel anathemaModel, final IResources resources) {
    return new IItemViewFactory() {
      public IItemView createView(IItem item) throws AnathemaException {
        return new CharmDescriptionView();
      }
    };
  }
}
