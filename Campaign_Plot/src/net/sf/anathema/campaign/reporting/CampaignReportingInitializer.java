package net.sf.anathema.campaign.reporting;

import net.sf.anathema.framework.reporting.IReportRegistry;
import net.sf.anathema.lib.resources.IResources;

public class CampaignReportingInitializer {

  public void initReporting(IReportRegistry reportRegistry, IResources resources) {
    reportRegistry.addReport(new NoteReport());
    reportRegistry.addReport(new MultiColumnSeriesReport(resources));
  }
}