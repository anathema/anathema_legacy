package net.sf.anathema.campaign.music.impl.view;

import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.anathema.campaign.music.impl.view.library.LibraryControlView;
import net.sf.anathema.campaign.music.impl.view.selection.MusicSelectionView;
import net.sf.anathema.campaign.music.presenter.ILibraryControlProperties;
import net.sf.anathema.campaign.music.presenter.IMusicPlayerProperties;
import net.sf.anathema.campaign.music.presenter.IMusicSelectionProperties;
import net.sf.anathema.campaign.music.presenter.ITrackDetailsProperties;
import net.sf.anathema.campaign.music.view.IMusicDatabaseView;
import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationProperties;
import net.sf.anathema.campaign.music.view.library.ILibraryControlView;
import net.sf.anathema.campaign.music.view.selection.IMusicSelectionView;
import net.sf.anathema.framework.view.item.AbstractItemView;
import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;

public class MusicDatabaseView extends AbstractItemView implements IMusicDatabaseView {

  private final JPanel content = new JPanel(new GridLayout(0, 1));

  public MusicDatabaseView(String name, Icon icon) {
    super(name, icon);
  }

  public JComponent getComponent() {
    return content;
  }

  public IMusicSelectionView addMusicSelectionView(
      ITableColumnViewSettings columnSettings,
      boolean playerView,
      IMusicCategorizationProperties categoryProperties,
      IMusicPlayerProperties playerProperties,
      IMusicSelectionProperties selectionProperties,
      ITrackDetailsProperties detailsProperties) {
    MusicSelectionView selectionView = new MusicSelectionView();
    selectionView.initGui(
        columnSettings,
        playerView,
        categoryProperties,
        playerProperties,
        selectionProperties,
        detailsProperties);
    content.add(selectionView.getComponent());
    return selectionView;
  }

  public ILibraryControlView addLibraryControlView(
      ITableColumnViewSettings settings,
      IMusicCategorizationProperties categorizationProperties,
      ILibraryControlProperties libraryProperties) {
    LibraryControlView libraryControlView = new LibraryControlView(settings, libraryProperties);
    libraryControlView.addLibraryView();
    libraryControlView.addSearchView(categorizationProperties);
    libraryControlView.initGui();
    content.add(libraryControlView.getComponent());
    return libraryControlView;
  }
}