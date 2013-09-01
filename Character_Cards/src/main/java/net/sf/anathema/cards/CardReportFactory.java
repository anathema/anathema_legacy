package net.sf.anathema.cards;

import net.sf.anathema.cards.data.providers.CharmCardDataProvider;
import net.sf.anathema.cards.data.providers.EquipmentCardDataProvider;
import net.sf.anathema.cards.data.providers.ICardDataProvider;
import net.sf.anathema.cards.data.providers.LegendCardDataProvider;
import net.sf.anathema.cards.data.providers.SpellCardDataProvider;
import net.sf.anathema.cards.layout.DemocritusCardLayout;
import net.sf.anathema.cards.layout.ICardLayout;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.initialization.RegisteredReportFactory;
import net.sf.anathema.initialization.reflections.Weight;

import java.util.ArrayList;
import java.util.List;

@RegisteredReportFactory
@Weight(weight = 40)
public class CardReportFactory implements IReportFactory {
  @Override
  public Report[] createReport(Environment environment, IApplicationModel model) {
    List<ICardDataProvider> dataProviders = new ArrayList<>();
    dataProviders.add(new CharmCardDataProvider(model, environment));
    dataProviders.add(new SpellCardDataProvider(model, environment));
    dataProviders.add(new EquipmentCardDataProvider(environment));
    dataProviders.add(new LegendCardDataProvider(environment));
    ICardLayout layout = new DemocritusCardLayout(.23f);
    return new Report[]{new CardReport(environment, layout, dataProviders.toArray(new ICardDataProvider[dataProviders.size()]))};
  }
}
