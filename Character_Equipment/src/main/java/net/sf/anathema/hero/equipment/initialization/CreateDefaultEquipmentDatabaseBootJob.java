package net.sf.anathema.hero.equipment.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IBootJob;
import net.sf.anathema.initialization.initialitems.ItemInitializer;
import net.sf.anathema.initialization.initialitems.RepositoryItemInitializationStrategy;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@BootJob
@Weight(weight = 11)
public class CreateDefaultEquipmentDatabaseBootJob implements IBootJob {

  @Override
  public void run(Resources resources, IApplicationModel anathemaModel) {
    RepositoryItemInitializationStrategy strategy = new EquipmentInitializationStrategy(anathemaModel);
    ResourceLoader resourceLoader = anathemaModel.getResourceLoader();
    new ItemInitializer(resources, strategy, resourceLoader).initialize();
  }
}