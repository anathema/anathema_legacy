package net.sf.anathema.campaign.music.impl.view.selection;

import net.miginfocom.layout.CC;
import net.sf.anathema.campaign.music.impl.view.SimpleTabViewFactory;
import net.sf.anathema.campaign.music.impl.view.player.MusicPlayerView;
import net.sf.anathema.campaign.music.model.selection.IMusicSelection;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.presenter.IMusicPlayerProperties;
import net.sf.anathema.campaign.music.presenter.IMusicSelectionProperties;
import net.sf.anathema.campaign.music.presenter.ITrackDetailsProperties;
import net.sf.anathema.campaign.music.presenter.selection.player.IMusicPlayerView;
import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationProperties;
import net.sf.anathema.campaign.music.view.selection.IMusicSelectionView;
import net.sf.anathema.campaign.music.view.selection.ITrackDetailsView;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.framework.view.util.TabDirection;
import net.sf.anathema.framework.view.util.TabbedView;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.container.TitledPanel;
import net.sf.anathema.lib.gui.list.actionview.ActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.EditableActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.IMultiSelectionActionAddableListView;
import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;
import net.sf.anathema.lib.gui.widgets.HorizontalLine;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MusicSelectionView implements IMusicSelectionView, IView {

  private JComponent content;
  private final SimpleTabViewFactory factory = new SimpleTabViewFactory();
  private ActionAddableListView<IMp3Track> trackListView;
  private EditableActionAddableListView<IMusicSelection> selectionsView;
  private final TrackDetailsView trackDetailsView = new TrackDetailsView();
  private MusicPlayerView playerView;

  public MusicSelectionView(JPanel content) {
    this.content = content;
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  public void initGui(ITableColumnViewSettings columnSettings, boolean includePlayerView,
                      IMusicCategorizationProperties categoryProperties, IMusicPlayerProperties playerProperties,
                      IMusicSelectionProperties selectionProperties, ITrackDetailsProperties detailsProperties) {
    selectionsView = new EditableActionAddableListView<IMusicSelection>(null, columnSettings, IMusicSelection.class);
     createContent(includePlayerView, selectionProperties, categoryProperties, playerProperties,
            detailsProperties);
  }

  private void createContent(boolean includePlayerView, IMusicSelectionProperties selectionProperties,
                                   IMusicCategorizationProperties categoryProperties,
                                   IMusicPlayerProperties playerProperties, ITrackDetailsProperties detailsProperties) {
    content.add(new JLabel(selectionProperties.getMusicSelectionBorderTitle()), new CC().split(2).spanX());
    content.add(new HorizontalLine(), new CC().grow());
    TabbedView selectionActionsView = new TabbedView(TabDirection.Down);
    selectionActionsView.addView(factory.createTabView(selectionsView.getComponent()),
            new ContentProperties(selectionProperties.getSelectionsString()));
    selectionActionsView.addView(
            factory.createTabView(trackDetailsView.getContent(categoryProperties, detailsProperties)),
            new ContentProperties(selectionProperties.getTrackDetailsString()));
    if (includePlayerView) {
      playerView = new MusicPlayerView();
      trackDetailsView.setPlayerComponent(playerView.getContent(playerProperties));
    } else {
      JLabel label = new JLabel(selectionProperties.getNoDecoderString() + ".", SwingConstants.CENTER); //$NON-NLS-1$
      trackDetailsView.setPlayerComponent(new TitledPanel(playerProperties.getPlayerBorderString(), label));
    }
    content.add(selectionActionsView.getComponent(), new CC().spanX(2).grow());
    TabbedView tracksView = new TabbedView(TabDirection.Down);
    trackListView = new ActionAddableListView<IMp3Track>(null, IMp3Track.class); //$NON-NLS-1$
    tracksView.addView(factory.createTabView(trackListView.getComponent()),
            new ContentProperties(selectionProperties.getCurrentSelectionString()));
    content.add(tracksView.getComponent(), new CC().grow());
  }

  @Override
  public IMultiSelectionActionAddableListView<IMp3Track> getTrackListView() {
    return trackListView;
  }

  @Override
  public IActionAddableListView<IMusicSelection> getSelectionListView() {
    return selectionsView;
  }

  @Override
  public ITrackDetailsView getTrackDetailsView() {
    return trackDetailsView;
  }

  @Override
  public IMusicPlayerView getPlayerView() {
    return playerView;
  }
}