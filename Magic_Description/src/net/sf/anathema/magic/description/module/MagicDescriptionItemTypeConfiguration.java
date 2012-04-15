package net.sf.anathema.magic.description.module;

import net.sf.anathema.framework.view.NullItemView;
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
public final class MagicDescriptionItemTypeConfiguration extends AbstractItemTypeConfiguration {

  public static final String MAGIC_DESCRIPTION_ITEM_TYPE_ID = "MagicDescription"; //$NON-NLS-1$
  private static final RepositoryConfiguration REPOSITORY_CONFIGURATION = new RepositoryConfiguration(".mdsc", "MagicDescription/");

  public MagicDescriptionItemTypeConfiguration() {
    super(new ItemType(MAGIC_DESCRIPTION_ITEM_TYPE_ID, REPOSITORY_CONFIGURATION, false));
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
  protected IItemViewFactory createItemViewFactory(IAnathemaModel anathemaModel, IResources resources) {
    return new IItemViewFactory() {
      @Override
      public IItemView createView(IItem item) throws AnathemaException {
        return new NullItemView();
      }
    };
  }
}
