package net.sf.anathema.campaign.music.impl.model.tracks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.campaign.music.model.track.Md5Checksum;

public class Mp3ChecksumCalculator {

  private int calculateEndOffset(byte[] fileData, boolean hasEndV2, boolean hasV1Tag) {
    int v1Offset = hasV1Tag ? 128 : 0;
    if (!hasEndV2) {
      return v1Offset;
    }
    int length = fileData.length;
    int tagEnd = length - v1Offset;
    int v2Size = ((fileData[tagEnd - 3] * 128 + fileData[tagEnd - 2]) * 128 + fileData[tagEnd - 1])
        * 128
        + fileData[tagEnd];
    int v2Offset = v2Size + 20;
    return v2Offset + v1Offset;
  }

  public Md5Checksum calculate(File file) throws IOException, NoSuchAlgorithmException {
    byte[] fileData = readFileToByteArray(file);
    boolean hasV1Tag = hasV1(fileData);
    boolean hasFrontV2 = hasFrontV2(fileData);
    boolean hasEndV2 = hasEndV2(fileData, hasV1Tag);
    int startOffset = calculateStartOffset(fileData, hasFrontV2);
    int endOffset = calculateEndOffset(fileData, hasEndV2, hasV1Tag);
    byte[] musicBytes = new byte[fileData.length - startOffset - endOffset];
    System.arraycopy(fileData, startOffset, musicBytes, 0, musicBytes.length);
    MessageDigest md5 = MessageDigest.getInstance("MD5"); //$NON-NLS-1$
    md5.update(musicBytes);
    byte[] digest = md5.digest();
    return new Md5Checksum(digest);
  }

  private int calculateStartOffset(byte[] fileData, boolean hasFrontV2) {
    if (!hasFrontV2) {
      return 0;
    }
    boolean footer = ((fileData[5] / 16) % 2 == 1);
    int dataSize = ((fileData[6] * 128 + fileData[7]) * 128 + fileData[8]) * 128 + fileData[9];
    int v2Size = 10 + dataSize + (footer ? 10 : 0);
    return v2Size;
  }

  private boolean hasEndV2(byte[] fileData, boolean hasV1Tag) {
    int length = fileData.length;
    int offset = hasV1Tag ? 128 : 0;
    int tagEnd = length - offset;
    String endV2String = new String(new byte[] { fileData[tagEnd - 10], fileData[tagEnd - 9], fileData[tagEnd - 8] });
    return endV2String.equals("3DI"); //$NON-NLS-1$
  }

  private boolean hasFrontV2(byte[] fileData) {
    String frontV2String = new String(new byte[] { fileData[0], fileData[1], fileData[2] });
    return frontV2String.equals("ID3"); //$NON-NLS-1$
  }

  private boolean hasV1(byte[] fileData) {
    int length = fileData.length;
    String v1String = new String(new byte[] { fileData[length - 128], fileData[length - 127], fileData[length - 126] });
    return v1String.equals("TAG"); //$NON-NLS-1$
  }

  private byte[] readFileToByteArray(File file) throws IOException {
    InputStream stream = null;
    try {
      stream = new FileInputStream(file);
      long length = file.length();
      if (length > Integer.MAX_VALUE) {
        throw new RuntimeException("Files > 2GB are too large to handle."); //$NON-NLS-1$
      }
      byte[] bytes = new byte[(int) length];
      int offset = 0;
      int bytesRead = 0;
      while (offset < bytes.length && bytesRead != -1) {
        bytesRead = stream.read(bytes, offset, (int) length - offset);
        offset += bytesRead;
      }
      return bytes;
    }
    finally {
      IOUtilities.close(stream);
    }
  }
}