package net.sf.anathema.campaign.music.export;

import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.lib.exception.AnathemaException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Mp3Utilities {

  public static Path getPreferredFile(IMp3Track track) {
    for (String fileReference : track.getFileReferences()) {
      Path mp3Path = Paths.get(fileReference);
      if (Files.exists(mp3Path)) {
        return mp3Path;
      }
    }
    throw new AnathemaException("No file found for track " + track);
  }

  public static String getPreferredFileReference(IMp3Track track) {
    for (String fileReference : track.getFileReferences()) {
      Path mp3Path = Paths.get(fileReference);
      if (Files.exists(mp3Path)) {
        return fileReference;
      }
    }
    return null;
  }
}