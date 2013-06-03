package net.sf.anathema.campaign.module;

import net.sf.anathema.campaign.item.PlotItemManagement;
import net.sf.anathema.campaign.item.PlotItemManagmentImpl;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.initialization.Extension;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.reflections.AnnotationFinder;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.registry.IRegistry;

@Extension(id="net.sf.anathema.campaign.management")
public class PlotItemManagementExtension implements IAnathemaExtension {

  public static PlotItemManagement getItemManagement(IApplicationModel model) {
    IRegistry<String,IAnathemaExtension> registry = model.getExtensionPointRegistry();
    PlotItemManagementExtension extension = (PlotItemManagementExtension) registry.get(ID);
    return extension.getManagementModel();
  }

  public static final String ID = "net.sf.anathema.campaign.management";
  private PlotItemManagement managementModel;

  @Override
  public void initialize(IDataFileProvider dataFileProvider, AnnotationFinder finder, ResourceLoader loader) throws InitializationException {
    managementModel = new PlotItemManagmentImpl();
  }

  public PlotItemManagement getManagementModel() {
    return managementModel;
  }
}
