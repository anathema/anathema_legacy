package net.sf.anathema.initialization.bootjob;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.Version;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IBootJob;
import net.sf.anathema.lib.logging.Logger;

import java.io.File;
import java.text.MessageFormat;

import static java.text.MessageFormat.format;

@BootJob
@Weight(weight = 5)
public class RepositoryUpdate implements IBootJob {
  private static final Logger logger = Logger.getLogger(RepositoryUpdate.class);

  @Override
  public void run(Environment environment, IApplicationModel model) {
    if (!repositoryAlreadyExists(model)) {
      createRepositoryAtVersion(environment, model);
      return;
    }
    ProxySplashscreen.getInstance().displayStatusMessage(environment.getString("Bootjob.UpdateRepository"));
    Version anathemaVersion = new Version(environment);
    RepositoryVersion repositoryVersion = new RepositoryVersion(model.getRepository());
    logger.info(format("Found repository at version {0}.", repositoryVersion.asString()));
    if (!repositoryVersion.needsUpdateTo(anathemaVersion)) {
      logger.info(format("No update necessary."));
      return;
    }
    logger.info(format("Updating to {0}.", anathemaVersion.asString()));
    repositoryVersion.updateTo(anathemaVersion);
  }

  private void createRepositoryAtVersion(Resources resources, IApplicationModel model) {
    Version anathemaVersion = new Version(resources);
    RepositoryVersion repositoryVersion = new RepositoryVersion(model.getRepository());
    logger.info(MessageFormat.format("No repository found. Creating repository for version {0}.", anathemaVersion.asString()));
    repositoryVersion.updateTo(anathemaVersion);
  }

  private boolean repositoryAlreadyExists(IApplicationModel model) {
    return new File(model.getRepository().getRepositoryPath()).exists();
  }
}