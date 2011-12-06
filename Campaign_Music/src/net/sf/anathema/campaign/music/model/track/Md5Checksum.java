package net.sf.anathema.campaign.music.model.track;

import java.util.Arrays;

public class Md5Checksum {

  private final byte[] md5Bytes;

  public Md5Checksum(byte[] md5Bytes) {
    this.md5Bytes = md5Bytes;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Md5Checksum)) {
      return false;
    }
    return Arrays.equals(md5Bytes, ((Md5Checksum) obj).md5Bytes);
  }

  @Override
  public int hashCode() {
    return md5Bytes.hashCode();
  }

  @Override
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    for (byte digestedByte : md5Bytes) {
      stringBuffer.append(Integer.toHexString(digestedByte).replaceAll("^f+", "-") + " "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
    return stringBuffer.toString();
  }
}