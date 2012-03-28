package net.sf.anathema.cards;

import net.sf.anathema.cards.reporting.CardReport;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.initialization.ReportFactory;
import net.sf.anathema.lib.resources.IResources;

@ReportFactory
public class CardReportFactory implements IReportFactory {
	  public Report[] createReport(IResources resources, IAnathemaModel model) {
		    return new Report[]{new CardReport(resources, model)};
		  }
}
