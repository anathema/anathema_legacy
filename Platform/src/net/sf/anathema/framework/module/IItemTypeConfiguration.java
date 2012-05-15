package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public interface IItemTypeConfiguration {

  void registerViewFactory(IAnathemaModel anathemaModel, IResources resources);

  IItemType getItemType();

  void initModel(IAnathemaModel model);

  void fillPresentationExtensionPoints(IRegistry<String, IAnathemaExtension> extensionPointRegistry, IResources resources, IAnathemaModel model,
                                       IAnathemaView view);
}