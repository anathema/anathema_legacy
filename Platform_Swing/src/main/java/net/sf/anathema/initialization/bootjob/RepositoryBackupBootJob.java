package net.sf.anathema.initialization.bootjob;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IBootJob;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@BootJob
@Weight(weight = 0)
//First back up the repo so all the other jobs can't do any harm
public class RepositoryBackupBootJob implements IBootJob {
  @Override
  public void run(Resources resources, IApplicationModel model) {
    new RepositoryBackup(resources, model).backupRepository();
  }
}
