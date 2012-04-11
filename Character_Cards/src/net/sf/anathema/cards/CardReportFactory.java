package net.sf.anathema.cards;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.cards.data.providers.CharmCardDataProvider;
import net.sf.anathema.cards.data.providers.EquipmentCardDataProvider;
import net.sf.anathema.cards.data.providers.ICardDataProvider;
import net.sf.anathema.cards.data.providers.LegendCardDataProvider;
import net.sf.anathema.cards.data.providers.SpellCardDataProvider;
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
		    List <ICardDataProvider> dataProviders = new ArrayList<ICardDataProvider>();
		  	dataProviders.add(new CharmCardDataProvider(model, resources));
		  	dataProviders.add(new SpellCardDataProvider(model, resources));
	  		dataProviders.add(new EquipmentCardDataProvider(resources));
		  	dataProviders.add(new LegendCardDataProvider(resources));
		    ICardLayout layout = new DemocritusCardLayout(resources, .23f);
		    return new Report[]{
		    		new CardReport(resources, layout, dataProviders.toArray(new ICardDataProvider[0]))};
		  }
}
