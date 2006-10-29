package net.sf.anathema.campaign.music.impl.view.selection;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import net.disy.commons.swing.border.TitledPanel;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
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
import net.sf.anathema.framework.view.util.TabDirection;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.framework.view.util.TabbedView;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.list.actionview.ActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.EditableActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.IMultiSelectionActionAddableListView;
import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;

public class MusicSelectionView implements IMusicSelectionView, IView {

  private JComponent content;
  private final SimpleTabViewFactory factory = new SimpleTabViewFactory();
  private ActionAddableListView<IMp3Track> trackListView;
  private EditableActionAddableListView<IMusicSelection> selectionsView;
  private final TrackDetailsView trackDetailsView = new TrackDetailsView();
  private MusicPlayerView playerView;

  public JComponent getComponent() {
    return content;
  }

  public void initGui(
      ITableColumnViewSettings columnSettings,
      boolean includePlayerView,
      IMusicCategorizationProperties categoryProperties,
      IMusicPlayerProperties playerProperties,
      IMusicSelectionProperties selectionProperties,
      ITrackDetailsProperties detailsProperties) {
    selectionsView = new EditableActionAddableListView<IMusicSelection>(
        selectionProperties.getSavedSelectionsTitle(),
        columnSettings,
        IMusicSelection.class);
    content = createContent(
        includePlayerView,
        selectionProperties,
        categoryProperties,
        playerProperties,
        detailsProperties);
  }

  private JComponent createContent(
      boolean includePlayerView,
      IMusicSelectionProperties selectionProperties,
      IMusicCategorizationProperties categoryProperties,
      IMusicPlayerProperties playerProperties,
      ITrackDetailsProperties detailsProperties) {
    JPanel panel = new JPanel(new GridDialogLayout(3, true));
    panel.setBorder(new TitledBorder(selectionProperties.getMusicSelectionBorderTitle()));
    TabbedView selectionActionsView = new TabbedView(TabDirection.Down);
    selectionActionsView.addTab(factory.createTabView(selectionsView.getComponent()), new ContentProperties(
        selectionProperties.getSelectionsString()));
    selectionActionsView.addTab(
        factory.createTabView(trackDetailsView.getContent(categoryProperties, detailsProperties)),
        new ContentProperties(selectionProperties.getTrackDetailsString()));
    if (includePlayerView) {
      playerView = new MusicPlayerView();
      trackDetailsView.setPlayerComponent(playerView.getContent(playerProperties));
    }
    else {
      JLabel label = new JLabel(selectionProperties.getNoDecoderString() + ".", SwingConstants.CENTER); //$NON-NLS-1$
      trackDetailsView.setPlayerComponent(new TitledPanel(playerProperties.getPlayerBorderString(), label));
    }
    GridDialogLayoutData tabbedPaneData = new GridDialogLayoutData(GridDialogLayoutData.FILL_BOTH);
    tabbedPaneData.setHorizontalSpan(2);
    panel.add(selectionActionsView.getComponent(), tabbedPaneData);
    TabbedView tracksView = new TabbedView(TabDirection.Down);
    trackListView = new ActionAddableListView<IMp3Track>(
        selectionProperties.getCurrentlySelectedTracksString() + ":", IMp3Track.class); //$NON-NLS-1$    
    tracksView.addTab(factory.createTabView(trackListView.getComponent()), new ContentProperties(
        selectionProperties.getCurrentSelectionString()));
    panel.add(tracksView.getComponent(), GridDialogLayoutData.FILL_BOTH);
    return panel;
  }

  public IMultiSelectionActionAddableListView<IMp3Track> getTrackListView() {
    return trackListView;
  }

  public IActionAddableListView<IMusicSelection> getSelectionListView() {
    return selectionsView;
  }

  public ITrackDetailsView getTrackDetailsView() {
    return trackDetailsView;
  }

  public IMusicPlayerView getPlayerView() {
    return playerView;
  }
}