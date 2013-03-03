package net.sf.anathema.initialization.bootjob;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IAnathemaBootJob;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@BootJob
@Weight(weight = 60)
public class DeprecatedElementRemovingBootJob implements IAnathemaBootJob {
  @Override
  public void run(IResources resources, IAnathemaModel model) {
    try {
      Path music = model.getRepository().getDataBaseDirectory("music");
      Files.delete(music);
    } catch (IOException e) {
      Logger.getLogger(DeprecatedElementRemovingBootJob.class).warn("Could not remove music folder.", e);
    }
  }
}