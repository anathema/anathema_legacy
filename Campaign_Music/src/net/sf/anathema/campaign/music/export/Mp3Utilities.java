package net.sf.anathema.campaign.music.export;

import java.io.File;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.campaign.music.model.track.IMp3Track;

public class Mp3Utilities {

  private Mp3Utilities() {
    throw new UnreachableCodeReachedException();
  }

  public static File getPreferredFile(IMp3Track track) {
    for (String fileReference : track.getFileReferences()) {
      File file = new File(fileReference);
      if (file.exists()) {
        return file;
      }
    }
    return null;
  }

  public static String getPreferredFileReference(IMp3Track track) {
    for (String fileReference : track.getFileReferences()) {
      File file = new File(fileReference);
      if (file.exists()) {
        return fileReference;
      }
    }
    return null;
  }
}