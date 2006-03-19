package net.sf.anathema.campaign.music.impl.view.selection;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import net.disy.commons.swing.border.TitledPanel;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.campaign.music.impl.view.player.MusicPlayerView;
import net.sf.anathema.campaign.music.presenter.IMusicPlayerProperties;
import net.sf.anathema.campaign.music.presenter.IMusicSelectionProperties;
import net.sf.anathema.campaign.music.presenter.ITrackDetailsProperties;
import net.sf.anathema.campaign.music.presenter.selection.player.IMusicPlayerView;
import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationProperties;
import net.sf.anathema.campaign.music.view.selection.IMusicSelectionView;
import net.sf.anathema.campaign.music.view.selection.ITrackDetailsView;
import net.sf.anathema.framework.presenter.view.ITabView;
import net.sf.anathema.framework.view.util.TabDirection;
import net.sf.anathema.framework.view.util.TabbedView;
import net.sf.anathema.lib.gui.list.actionview.ActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.EditableActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.IMultiSelectionActionAddableListView;
import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;

public class MusicSelectionView implements IMusicSelectionView {

  private JComponent content;
  private ActionAddableListView trackListView;
  private EditableActionAddableListView selectionsView;
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
      IMusicSelectionProperties selectionProperties, ITrackDetailsProperties detailsProperties) {
    selectionsView = new EditableActionAddableListView(selectionProperties.getSavedSelectionsTitle(), columnSettings);
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
      IMusicPlayerProperties playerProperties, ITrackDetailsProperties detailsProperties) {
    JPanel panel = new JPanel(new GridDialogLayout(3, true));
    panel.setBorder(new TitledBorder(selectionProperties.getMusicSelectionBorderTitle()));
    TabbedView selectionActionsView = new TabbedView(TabDirection.Down);
    selectionActionsView.addTab(createITabView(selectionsView.getContent()), selectionProperties.getSelectionsString());
    selectionActionsView.addTab(
        createITabView(trackDetailsView.getContent(categoryProperties, detailsProperties)),
        selectionProperties.getTrackDetailsString());
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
    trackListView = new ActionAddableListView(selectionProperties.getCurrentlySelectedTracksString() + ":"); //$NON-NLS-1$
    tracksView.addTab(createITabView(trackListView.getContent()), selectionProperties.getCurrentSelectionString());
    panel.add(tracksView.getComponent(), GridDialogLayoutData.FILL_BOTH);
    return panel;
  }

  private ITabView createITabView(final JComponent component) {
    return new ITabView() {

      public void initGui(Object properties) {
        // Nothing to do
      }

      public JComponent getComponent() {
        return component;
      }

      public boolean needsScrollbar() {
        return false;
      }
    };
  }

  public IMultiSelectionActionAddableListView getTrackListView() {
    return trackListView;
  }

  public IActionAddableListView getSelectionListView() {
    return selectionsView;
  }

  public ITrackDetailsView getTrackDetailsView() {
    return trackDetailsView;
  }

  public IMusicPlayerView getPlayerView() {
    return playerView;
  }
}