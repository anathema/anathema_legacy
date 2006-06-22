package net.sf.anathema.campaign.module;

import net.sf.anathema.campaign.reporting.CampaignReportingInitializer;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.AbstractAnathemaModule;
import net.sf.anathema.lib.resources.IResources;

public class CampaignModule extends AbstractAnathemaModule {

  public CampaignModule() {
    addItemTypeConfiguration(new NoteTypeConfiguration());
    addItemTypeConfiguration(new SeriesTypeConfiguration());
  }

  @Override
  public void initModel(IAnathemaModel model, IResources resources) {
    super.initModel(model, resources);
    new CampaignReportingInitializer().initReporting(model.getReportRegistry(), resources);
  }
}