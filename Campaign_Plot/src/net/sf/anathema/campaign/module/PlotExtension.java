package net.sf.anathema.campaign.module;

import net.sf.anathema.campaign.item.PlotItemManagement;
import net.sf.anathema.campaign.item.PlotItemManagmentImpl;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.PlotItemViewFactory;
import net.sf.anathema.initialization.Extension;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.reflections.AnnotationFinder;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.Registry;

@Extension(id = "net.sf.anathema.campaign.plot.extension")
public class PlotExtension implements IAnathemaExtension {

  public static PlotExtension getExtension(IApplicationModel model) {
    IRegistry<String, IAnathemaExtension> registry = model.getExtensionPointRegistry();
    return (PlotExtension) registry.get(ID);
  }

  public static PlotItemManagement getItemManagement(IApplicationModel model) {
    PlotExtension extension = getExtension(model);
    return extension.getManagementModel();
  }

  public static final String ID = "net.sf.anathema.campaign.plot.extension";
  private PlotItemManagement managementModel;
  private IRegistry<IItemType, PlotItemViewFactory> viewFactoryRegistry;

  @Override
  public void initialize(IDataFileProvider dataFileProvider, AnnotationFinder finder, ResourceLoader loader) throws InitializationException {
    managementModel = new PlotItemManagmentImpl();
    viewFactoryRegistry = new Registry<>();
  }

  public PlotItemManagement getManagementModel() {
    return managementModel;
  }

  public IRegistry<IItemType, PlotItemViewFactory> getViewFactoryRegistry() {
    return viewFactoryRegistry;
  }
}
