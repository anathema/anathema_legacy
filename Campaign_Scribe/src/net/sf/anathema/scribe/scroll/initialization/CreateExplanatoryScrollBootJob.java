package net.sf.anathema.scribe.scroll.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IBootJob;
import net.sf.anathema.initialization.initialitems.ItemInitializer;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@BootJob
@Weight(weight = 12)
public class CreateExplanatoryScrollBootJob implements IBootJob {

  @Override
  public void run(Resources resources, IApplicationModel anathemaModel) {
    ScrollInitializationStrategy strategy = new ScrollInitializationStrategy(anathemaModel);
    ResourceLoader resourceLoader = anathemaModel.getResourceLoader();
    new ItemInitializer(resources, strategy, resourceLoader).initialize();
  }
}