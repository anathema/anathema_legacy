package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.model.AnathemaModel;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.repository.filechooser.RepositoryFileChooserPropertiesExtensionPoint;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractPersistableItemTypeConfiguration extends AbstractItemTypeConfiguration {

  public AbstractPersistableItemTypeConfiguration(IItemType type) {
    super(type);
  }

  @Override
  public void fillPresentationExtensionPoints(
      IRegistry<String, IAnathemaExtension> extensionPointRegistry,
      IResources resources,
      IAnathemaModel model,
      IAnathemaView view) {
    super.fillPresentationExtensionPoints(extensionPointRegistry, resources, model, view);
    registerRepositoryFileChooserProperties(extensionPointRegistry, resources);
  }

  private void registerRepositoryFileChooserProperties(
      IRegistry<String, IAnathemaExtension> extensionPointRegistry,
      IResources resources) {
    RepositoryFileChooserPropertiesExtensionPoint repositoryExtensionPoint = (RepositoryFileChooserPropertiesExtensionPoint) extensionPointRegistry.get(RepositoryFileChooserPropertiesExtensionPoint.ID);
    repositoryExtensionPoint.register(getItemType(), new DefaultObjectSelectionProperties(
        resources,
        getLoadMessageKey(),
        getLoadTitleKey()));
  }

  @Override
  public void initModel(AnathemaModel model) {
    model.getPersisterRegistry().register(getItemType(), createPersister(model));
  }

  protected abstract String getLoadMessageKey();

  protected abstract String getLoadTitleKey();

  protected abstract IRepositoryItemPersister createPersister(IAnathemaModel model);
}