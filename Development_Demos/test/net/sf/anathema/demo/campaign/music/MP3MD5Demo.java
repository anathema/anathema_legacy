package net.sf.anathema.demo.campaign.music;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.sf.anathema.campaign.music.impl.model.tracks.Mp3ChecksumCalculator;
import net.sf.anathema.campaign.music.model.track.Md5Checksum;
import net.sf.anathema.lib.gui.file.FileChoosingUtilities;

public class MP3MD5Demo {

  public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
    File file = FileChoosingUtilities.chooseFile("Select MP3", null, null); //$NON-NLS-1$
    Md5Checksum checksum = new Mp3ChecksumCalculator().calculate(file);
    System.err.println(checksum);
  }
}