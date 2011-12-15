package net.sf.anathema.framework.environment;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.configuration.AnathemaPreferences;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.IAnathemaBootJob;
import net.sf.anathema.lib.resources.IResources;

public class LookAndFeelInitializerBootJob implements IAnathemaBootJob {
  @Override
  public void run(IResources resources, IAnathemaModel model, IAnathemaView view) {
    AnathemaEnvironment.initLookAndFeel(AnathemaPreferences.getDefaultPreferences());
  }
}
