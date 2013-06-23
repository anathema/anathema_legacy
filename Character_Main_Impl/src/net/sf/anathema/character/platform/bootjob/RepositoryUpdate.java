package net.sf.anathema.character.platform.bootjob;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.Version;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IBootJob;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.Resources;

import java.io.File;
import java.text.MessageFormat;

import static java.text.MessageFormat.format;

@BootJob
@Weight(weight = 5)
public class RepositoryUpdate implements IBootJob {
  private static final Logger logger = Logger.getLogger(RepositoryUpdate.class);

  @Override
  public void run(Resources resources, IApplicationModel model) {
    if (!repositoryAlreadyExists(model)) {
      createRepositoryAtVersion(resources, model);
      return;
    }
    ProxySplashscreen.getInstance().displayStatusMessage(resources.getString("Hero.Bootjob.Cleanup.Splashmessage"));
    Version anathemaVersion = new Version(resources);
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