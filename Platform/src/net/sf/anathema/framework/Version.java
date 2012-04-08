package net.sf.anathema.framework;

import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;

public class Version implements Comparable<Version> {

  private int majorVersion;
  private int minorVersion;
  private int revision;

  public Version(int major, int minor, int revision) {
    this.majorVersion = major;
    this.minorVersion = minor;
    this.revision = revision;
  }

  public Version(IResources resources) {
    this(resources.getString("Anathema.Version.Numeric"));
  }

  public Version(String versionString) {
    try {
      parseVersion(versionString);
    } catch (NumberFormatException e) {
      Logger.getLogger(Version.class).warn("Could not parse version. Assuming max.");
      this.majorVersion = Integer.MAX_VALUE;
      this.minorVersion = Integer.MAX_VALUE;
      this.revision = Integer.MAX_VALUE;
    }
  }

  private void parseVersion(String string) {
    String[] split = string.split("\\."); //$NON-NLS-1$ //$NON-NLS-2$
    majorVersion = Integer.valueOf(split[0]);
    minorVersion = Integer.valueOf(split[1]);
    if (split.length > 2) {
      revision = Integer.valueOf(split[2]);
    } else {
      revision = 0;
    }
  }

  public void updateTo(Version version) {
    if (compareTo(version) < 0) {
      majorVersion = version.majorVersion;
      minorVersion = version.minorVersion;
      revision = version.revision;
    }
  }

  @Override
  public int compareTo(Version version) {
    if (version.getClass() != getClass()) {
      throw new ClassCastException("Uncomparable objects.");
    }

    if (majorVersion != version.majorVersion) {
      return majorVersion - version.majorVersion;
    }
    if (minorVersion != version.minorVersion) {
      return minorVersion - version.minorVersion;
    }
    if (revision != version.revision) {
      return revision - version.revision;
    }
    return 0;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != getClass()) {
      return false;
    }

    return compareTo((Version) obj) == 0;
  }

  @Override
  public int hashCode() {
    return majorVersion * 3 + minorVersion * 7 + revision * 13;
  }

  public boolean isLargerThan(Version otherVersion) {
    return otherVersion == null || compareTo(otherVersion) > 0;
  }

  public String asString() {
    return majorVersion + "." + minorVersion + "." + revision;
  }
}