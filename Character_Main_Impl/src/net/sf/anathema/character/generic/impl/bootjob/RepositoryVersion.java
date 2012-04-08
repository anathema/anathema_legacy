package net.sf.anathema.character.generic.impl.bootjob;

import net.sf.anathema.framework.Version;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.lib.logging.Logger;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class RepositoryVersion {
  private static final Logger logger = Logger.getLogger(RepositoryVersion.class);

  private final IRepository repository;

  public RepositoryVersion(IRepository repository) {
    this.repository = repository;
  }

  public void updateTo(Version anathemaVersion) {
    try {
      FileUtils.writeStringToFile(getVersionFile(repository), anathemaVersion.asString());
    } catch (IOException e) {
      logger.warn("Could not update repository version.");
    }
  }

  public boolean needsUpdateTo(Version anathemaVersion) {
    return anathemaVersion.isLargerThan(getVersion());
  }

  private Version getVersion() {
    File versionFile = getVersionFile(repository);
    if (!versionFile.exists()) {
      return new Version(0, 0, 0);
    } else {
      return readVersionFromFile(versionFile);
    }
  }

  private Version readVersionFromFile(File versionFile) {
    try {
      String versionString = FileUtils.readFileToString(versionFile);
      return new Version(versionString);
    } catch (IOException e) {
      logger.warn("Could not determine repository version, assuming outdated repository.");
      return new Version(0, 0, 0);
    }
  }

  private File getVersionFile(IRepository repository) {
    return new File(repository.getRepositoryPath(), "repository.version");
  }

  public String asString() {
    return getVersion().asString();
  }
}