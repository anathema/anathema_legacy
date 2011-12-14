package net.sf.anathema.demo.campaign.music;

import de.jdemo.extensions.SwingDemoCase;
import de.jdemo.junit.DemoAsTestRunner;
import net.sf.anathema.campaign.music.impl.model.player.MusicPlayerModel;
import net.sf.anathema.campaign.music.impl.model.tracks.FileMp3Track;
import net.sf.anathema.campaign.music.impl.view.player.MusicPlayerView;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.presenter.MusicPlayerProperties;
import net.sf.anathema.campaign.music.presenter.selection.player.IMusicPlayerModel;
import net.sf.anathema.campaign.music.presenter.selection.player.MusicPlayerPresenter;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.lib.gui.file.FileChoosingUtilities;
import net.sf.anathema.lib.resources.IResources;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(DemoAsTestRunner.class)
public class MusicPlayerDemo extends SwingDemoCase {

  public void demo() throws Exception {
    MusicPlayerView playerView = new MusicPlayerView();
    File file = FileChoosingUtilities.chooseFile("Okay", createFrame(), null); //$NON-NLS-1$
    if (file == null) {
      return;
    }
    IMp3Track mp3Track = new FileMp3Track(file);
    IMusicPlayerModel playerModel = new MusicPlayerModel();
    new MusicPlayerPresenter(new AnathemaResources(), playerView, playerModel, null).initPresentation();
    playerModel.setTrack(mp3Track);
    IResources resources = new AnathemaResources();
    show(playerView.getContent(new MusicPlayerProperties(resources)));
  }
}