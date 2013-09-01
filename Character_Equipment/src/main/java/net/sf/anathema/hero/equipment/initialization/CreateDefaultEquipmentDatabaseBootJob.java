package net.sf.anathema.hero.equipment.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.ResourceLoader;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IBootJob;
import net.sf.anathema.initialization.initialitems.ItemInitializer;
import net.sf.anathema.initialization.initialitems.RepositoryItemInitializationStrategy;

@BootJob
@Weight(weight = 11)
public class CreateDefaultEquipmentDatabaseBootJob implements IBootJob {

  @Override
  public void run(Environment environment, IApplicationModel anathemaModel) {
    RepositoryItemInitializationStrategy strategy = new EquipmentInitializationStrategy(anathemaModel);
    new ItemInitializer(environment, strategy).initialize();
  }
}