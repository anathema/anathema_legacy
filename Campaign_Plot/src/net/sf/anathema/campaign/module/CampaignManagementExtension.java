package net.sf.anathema.campaign.module;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.model.ItemManagmentModel;
import net.sf.anathema.framework.presenter.IItemManagementModel;
import net.sf.anathema.initialization.Extension;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.reflections.AnnotationFinder;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.registry.IRegistry;

@Extension(id="net.sf.anathema.campaign.management")
public class CampaignManagementExtension implements IAnathemaExtension {

  public static IItemManagementModel getItemManagement(IApplicationModel model) {
    IRegistry<String,IAnathemaExtension> registry = model.getExtensionPointRegistry();
    CampaignManagementExtension extension = (CampaignManagementExtension) registry.get(ID);
    return extension.getManagementModel();
  }

  public static final String ID = "net.sf.anathema.campaign.management";
  private IItemManagementModel managementModel;

  @Override
  public void initialize(IDataFileProvider dataFileProvider, AnnotationFinder finder, ResourceLoader loader) throws InitializationException {
    managementModel = new ItemManagmentModel();
  }

  public IItemManagementModel getManagementModel() {
    return managementModel;
  }
}
