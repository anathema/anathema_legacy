package net.sf.anathema.framework.presenter.item;

import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.initialization.Extension;
import net.sf.anathema.initialization.reflections.AnnotationFinder;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.registry.Registry;

@Extension(id = "net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint")
public class ItemTypeCreationViewPropertiesExtensionPoint extends Registry<IItemType, IItemTypeViewProperties> implements IAnathemaExtension {

  public static final String ID = ItemTypeCreationViewPropertiesExtensionPoint.class.getName();

  @Override
  public void initialize(IDataFileProvider dataFileProvider, AnnotationFinder finder, ResourceLoader loader) {
    // nothing to do
  }
}