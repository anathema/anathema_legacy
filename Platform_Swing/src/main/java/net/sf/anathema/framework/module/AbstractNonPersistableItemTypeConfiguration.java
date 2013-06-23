package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.view.ApplicationView;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.Resources;

public abstract class AbstractNonPersistableItemTypeConfiguration implements ItemTypeConfiguration {

  private IItemType type;

  public AbstractNonPersistableItemTypeConfiguration(IItemType type) {
    this.type = type;
  }

  @Override
  public IItemType getItemType() {
    return type;
  }

  @Override
  public final void fillPresentationExtensionPoints(IRegistry<String, IAnathemaExtension> extensionPointRegistry, Resources resources,
                                                    IApplicationModel model, ApplicationView view) {
    //nothing to do
  }
}