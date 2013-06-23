package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.view.ApplicationView;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.Resources;

public interface ItemTypeConfiguration {

  IItemType getItemType();

  void fillPresentationExtensionPoints(IRegistry<String, IAnathemaExtension> extensionPointRegistry, Resources resources, IApplicationModel model,
                                       ApplicationView view);
}