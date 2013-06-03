package net.sf.anathema.magic.description.module;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.module.IItemTypeConfiguration;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;
import net.sf.anathema.framework.view.ApplicationView;
import net.sf.anathema.initialization.ItemTypeConfiguration;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.Resources;

@ItemTypeConfiguration
public final class MagicDescriptionItemTypeConfiguration implements IItemTypeConfiguration {

  public static final String MAGIC_DESCRIPTION_ITEM_TYPE_ID = "MagicDescription";
  private static final RepositoryConfiguration REPOSITORY_CONFIGURATION = new RepositoryConfiguration(".mdsc", "MagicDescription/");
  private final ItemType itemType = new ItemType(MAGIC_DESCRIPTION_ITEM_TYPE_ID, REPOSITORY_CONFIGURATION, false);

  @Override
  public void registerViewFactory(IApplicationModel anathemaModel, Resources resources) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public IItemType getItemType() {
    return  itemType;
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
}
