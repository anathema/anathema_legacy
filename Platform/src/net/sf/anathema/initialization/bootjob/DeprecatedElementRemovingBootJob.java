package net.sf.anathema.initialization.bootjob;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IBootJob;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Path;

@BootJob
@Weight(weight = 60)
public class DeprecatedElementRemovingBootJob implements IBootJob {
  @Override
  public void run(IResources resources, IApplicationModel model) {
    try {
      Path music = model.getRepository().getDataBaseDirectory("music");
      FileUtils.forceDelete(music.toFile());
    } catch (IOException e) {
      Logger.getLogger(DeprecatedElementRemovingBootJob.class).warn("Could not remove music folder.", e);
    }
  }
}