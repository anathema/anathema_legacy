package net.sf.anathema.framework;

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
    String[] split = resources.getString("Anathema.Version.Numeric").split("\\."); //$NON-NLS-1$ //$NON-NLS-2$
    majorVersion = Integer.valueOf(split[0]);
    minorVersion = Integer.valueOf(split[1]);
    if (split.length > 2) {
      revision = Integer.valueOf(split[2]);
    }
    else {
      revision = 0;
    }
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

  public void updateTo(Version version) {
    if (compareTo(version) < 0) {
      majorVersion = version.majorVersion;
      minorVersion = version.minorVersion;
      revision = version.revision;
    }
  }

  @Override
  public int compareTo(Version version) {
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
}