package net.sf.anathema.magic.description.module;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;
import net.sf.anathema.framework.view.ApplicationView;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.framework.view.NullItemView;
import net.sf.anathema.initialization.ItemTypeConfiguration;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.Resources;

@ItemTypeConfiguration
public final class MagicDescriptionItemTypeConfiguration extends AbstractItemTypeConfiguration {

  public static final String MAGIC_DESCRIPTION_ITEM_TYPE_ID = "MagicDescription";
  private static final RepositoryConfiguration REPOSITORY_CONFIGURATION = new RepositoryConfiguration(".mdsc", "MagicDescription/");

  public MagicDescriptionItemTypeConfiguration() {
    super(new ItemType(MAGIC_DESCRIPTION_ITEM_TYPE_ID, REPOSITORY_CONFIGURATION, false));
  }

  @Override
  public void initModel(IApplicationModel model) {
    // nothing to do
  }

  @Override
  public void fillPresentationExtensionPoints(IRegistry<String, IAnathemaExtension> extensionPointRegistry, Resources resources,
                                              IApplicationModel model, ApplicationView view) {
    // nothing to do
  }

  @Override
  protected IItemViewFactory createItemViewFactory(IApplicationModel model, Resources resources) {
    return new IItemViewFactory() {
      @Override
      public IItemView createView(IItem item) throws AnathemaException {
        return new NullItemView();
      }
    };
  }
}
