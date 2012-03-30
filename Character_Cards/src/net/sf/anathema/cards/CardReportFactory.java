package net.sf.anathema.cards;

import net.sf.anathema.cards.data.providers.CharmCardProvider;
import net.sf.anathema.cards.data.providers.ICardDataProvider;
import net.sf.anathema.cards.data.providers.SpellCardProvider;
import net.sf.anathema.cards.layout.DemocritusCardLayout;
import net.sf.anathema.cards.layout.ICardLayout;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.initialization.ReportFactory;
import net.sf.anathema.lib.resources.IResources;

@ReportFactory
public class CardReportFactory implements IReportFactory {
	  public Report[] createReport(IResources resources, IAnathemaModel model) {
		    // potenially offer different reports with differing
		    // provider mixtures in the future, layouts
		    ICardDataProvider charmCards = new CharmCardProvider(model, resources);
		    ICardDataProvider spellCards = new SpellCardProvider(model, resources);
		    ICardLayout layout = new DemocritusCardLayout(resources, .23f);
		    return new Report[]{new CardReport(resources, layout, charmCards, spellCards)};
		  }
}
