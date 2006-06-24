package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IExtensionPoint;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public interface IAnathemaModule {

  public void initModelExtensionPoints(IRegistry<String, IExtensionPoint> registry, IAnathemaModel model, IResources resources);

  public void initModel(IAnathemaModel anathemaModel, IResources resources);

  public void initPresentationExtensionPoints(IRegistry<String, IExtensionPoint> registry, IResources resources);

  public void initPresentation(IResources resources, IAnathemaModel model, IAnathemaView anathemaView);

  public void fillModelExtensionPoints(IRegistry<String, IExtensionPoint> extensionPointRegistry, IAnathemaModel model);

  public void fillPresentationExtensionPoints(
      IRegistry<String, IExtensionPoint> extensionPointRegistry,
      IAnathemaModel model,
      IResources resources,
      IAnathemaView view);
}