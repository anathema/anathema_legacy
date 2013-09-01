package net.sf.anathema.scribe.scroll.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IBootJob;
import net.sf.anathema.initialization.initialitems.ItemInitializer;

@BootJob
@Weight(weight = 12)
public class CreateExplanatoryScrollBootJob implements IBootJob {

  @Override
  public void run(Environment environment, IApplicationModel anathemaModel) {
    ScrollInitializationStrategy strategy = new ScrollInitializationStrategy(anathemaModel);
    new ItemInitializer(environment, strategy).initialize();
  }
}