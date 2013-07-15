package net.sf.anathema.framework;

import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.Resources;

public class Version implements Comparable<Version> {

  public static boolean isParseable(String versionString) {
    try {
      String[] split = versionString.split("\\.");
      for (String numbers : split) {
        Integer.valueOf(numbers);
      }
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private int majorVersion;
  private int minorVersion;
  private int revision;

  public Version(int major, int minor, int revision) {
    this.majorVersion = major;
    this.minorVersion = minor;
    this.revision = revision;
  }

  public Version(Resources resources) {
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
    String[] split = string.split("\\.");
    majorVersion = Integer.valueOf(split[0]);
    minorVersion = Integer.valueOf(split[1]);
    if (split.length > 2) {
      revision = Integer.valueOf(split[2]);
    } else {
      revision = 0;
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

  @SuppressWarnings("SimplifiableIfStatement")
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

  public int getMajorVersion() {
      return majorVersion;
  }

  public int getMinorVersion() {
      return minorVersion;
  }

  public int getRevision() {
      return revision;
  }
}