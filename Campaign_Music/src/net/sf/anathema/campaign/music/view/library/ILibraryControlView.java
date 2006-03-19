package net.sf.anathema.campaign.music.view.library;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.campaign.music.presenter.library.ILibraryListView;
import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationView;
import net.sf.anathema.campaign.music.view.search.ISearchComponent;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;

public interface ILibraryControlView extends ILibraryListView {

  public ISearchComponent addSearchParameter(String string);

  public IActionAddableListView getLibraryView();

  public IMusicCategorizationView getSearchMusicCategorizationView();

  public IActionAddableListView getTrackListView();

  public void initGui();

  public void setSearchAction(SmartAction action);
}