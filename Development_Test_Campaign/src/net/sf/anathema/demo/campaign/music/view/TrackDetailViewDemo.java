package net.sf.anathema.demo.campaign.music.view;

import javax.swing.JComponent;

import net.sf.anathema.campaign.music.impl.view.selection.TrackDetailsView;
import net.sf.anathema.campaign.music.model.selection.ITrackDetailModel;
import net.sf.anathema.campaign.music.presenter.MusicCategorizationProperties;
import net.sf.anathema.campaign.music.presenter.TrackDetailsPresenter;
import net.sf.anathema.campaign.music.presenter.TrackDetailsProperties;
import net.sf.anathema.demo.campaign.music.DemoMp3Track;
import net.sf.anathema.framework.resources.AnathemaResources;
import de.jdemo.extensions.SwingDemoCase;

public class TrackDetailViewDemo extends SwingDemoCase {

  public void demoDetailViewWithoutTrack() throws Exception {
    AnathemaResources resources = new AnathemaResources();
    TrackDetailsView view = new TrackDetailsView();
    ITrackDetailModel model = new DemoTrackDetailModel();
    new TrackDetailsPresenter(resources, view, model).initPresentation();
    show(view.getContent(new MusicCategorizationProperties(resources), new TrackDetailsProperties(resources)));
  }

  private DemoMp3Track createDemoTrack(int index) {
    return new DemoMp3Track("Album " + index, "Artist " + index, "Track " + index, "Title " + index); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
  }

  public void demoDetailViewWithTrack() throws Exception {
    AnathemaResources resources = new AnathemaResources();
    TrackDetailsView view = new TrackDetailsView();
    DemoTrackDetailModel model = new DemoTrackDetailModel();
    new TrackDetailsPresenter(resources, view, model).initPresentation();
    JComponent content = view.getContent(new MusicCategorizationProperties(resources), new TrackDetailsProperties(
        resources));
    model.setSelectedTrack(createDemoTrack(1));
    show(content);
  }
}