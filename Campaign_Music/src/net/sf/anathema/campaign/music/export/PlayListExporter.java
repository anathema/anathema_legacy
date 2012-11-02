package net.sf.anathema.campaign.music.export;

import net.sf.anathema.campaign.music.model.track.IMp3Track;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PlayListExporter {

  public List<IMp3Track> export(Writer writer, IMp3Track[] tracks) {
    PrintWriter printWriter = new PrintWriter(writer);
    List<IMp3Track> missingTracks = new ArrayList<>();
    for (IMp3Track track : tracks) {
      String fileReference = Mp3Utilities.getPreferredFileReference(track);
      if (fileReference == null) {
        missingTracks.add(track);
        printWriter.println(track.getFileReferences()[0]);
      }
      else {
        printWriter.println(fileReference);
      }
    }
    return missingTracks;
  }
}