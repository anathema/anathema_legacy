package net.sf.anathema.character.platform.bootjob;

import net.sf.anathema.framework.Version;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.lib.io.PathUtils;
import net.sf.anathema.lib.logging.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RepositoryVersion {
  private static final Logger logger = Logger.getLogger(RepositoryVersion.class);

  private final Repository repository;

  public RepositoryVersion(Repository repository) {
    this.repository = repository;
  }

  public void updateTo(Version anathemaVersion) {
    try {
      Path versionFile = getVersionFile(repository);
      PathUtils.writeStringToFile(versionFile, anathemaVersion.asString());
    } catch (IOException e) {
      logger.warn("Could not update repository version.");
    }
  }

  public boolean needsUpdateTo(Version anathemaVersion) {
    return anathemaVersion.isLargerThan(getVersion());
  }

  private Version getVersion() {
    Path versionFile = getVersionFile(repository);
    if (!Files.exists(versionFile)) {
      return new Version(0, 0, 0);
    } else {
      return readVersionFromFile(versionFile);
    }
  }

  private Version readVersionFromFile(Path versionFile) {
    try {
      String versionString = PathUtils.readFileToString(versionFile);
      return new Version(versionString);
    } catch (IOException e) {
      logger.warn("Could not determine repository version, assuming outdated repository.");
      return new Version(0, 0, 0);
    }
  }

  private Path getVersionFile(Repository repository) {
    return Paths.get(repository.getRepositoryPath()).resolve("repository.version");
  }

  public String asString() {
    return getVersion().asString();
  }
}