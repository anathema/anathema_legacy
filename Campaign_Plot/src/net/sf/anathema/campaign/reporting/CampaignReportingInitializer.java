package net.sf.anathema.campaign.reporting;

import net.sf.anathema.framework.reporting.IReportRegistry;

public class CampaignReportingInitializer {

  public void initReporting(IReportRegistry reportRegistry) {
    reportRegistry.addReport(new NoteReport());
    reportRegistry.addReport(new SeriesReport());
    reportRegistry.addReport(new MultiColumnSeriesReport());
  }
}