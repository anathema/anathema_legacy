package net.sf.anathema.scribe.scroll.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IBootJob;
import net.sf.anathema.initialization.initialitems.ItemInitializer;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@BootJob
@Weight(weight = 12)
public class CreateExplanatoryScrollBootJob implements IBootJob {

  @Override
  public void run(Resources resources, IApplicationModel anathemaModel) {
    ScrollInitializationStrategy strategy = new ScrollInitializationStrategy(anathemaModel);
    new ItemInitializer(anathemaModel, resources, strategy).initialize();
  }
}