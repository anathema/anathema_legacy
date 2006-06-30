package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public interface IAnathemaModule {

  public void initModelExtensionPoints(IRegistry<String, IAnathemaExtension> registry, IAnathemaModel model, IResources resources);

  public void initModel(IAnathemaModel anathemaModel);

  public void initPresentationExtensionPoints(IRegistry<String, IAnathemaExtension> registry, IResources resources);

  public void initPresentation(IResources resources, IAnathemaModel model, IAnathemaView anathemaView);

  public void fillModelExtensionPoints(IRegistry<String, IAnathemaExtension> extensionPointRegistry, IAnathemaModel model);

  public void fillPresentationExtensionPoints(
      IRegistry<String, IAnathemaExtension> extensionPointRegistry,
      IAnathemaModel model,
      IResources resources,
      IAnathemaView view);
}