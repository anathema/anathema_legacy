package net.sf.anathema.campaign.module;

import net.sf.anathema.campaign.reporting.CampaignReportingInitializer;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.AbstractAnathemaModule;
import net.sf.anathema.framework.resources.IAnathemaResources;

public class CampaignModule extends AbstractAnathemaModule {

  public CampaignModule() {
    addItemTypeConfiguration(new NoteTypeConfiguration());
    addItemTypeConfiguration(new SeriesTypeConfiguration());
  }

  @Override
  public void initModel(IAnathemaModel model) {
    super.initModel(model);
    new CampaignReportingInitializer().initReporting(model.getReportRegistry(), getResources());
  }

  @Override
  public void initAnathemaResources(IAnathemaResources resources) {
    super.initAnathemaResources(resources);
    resources.addStringResourceHandler(createStringProvider("Campaign", resources.getLocale())); //$NON-NLS-1$
  }
}