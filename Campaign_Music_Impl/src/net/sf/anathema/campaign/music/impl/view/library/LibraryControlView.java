package net.sf.anathema.campaign.music.impl.view.library;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.campaign.music.impl.view.SimpleTabViewFactory;
import net.sf.anathema.campaign.music.impl.view.categorization.MusicCategorizationView;
import net.sf.anathema.campaign.music.model.libary.ILibrary;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.presenter.ILibraryControlProperties;
import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationProperties;
import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationView;
import net.sf.anathema.campaign.music.view.library.ILibraryControlView;
import net.sf.anathema.campaign.music.view.search.ISearchComponent;
import net.sf.anathema.framework.view.util.TabDirection;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.framework.view.util.TabbedView;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.list.actionview.ActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.EditableActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;

public class LibraryControlView implements ILibraryControlView, IView {

  private final JPanel content = new JPanel();
  private final SimpleTabViewFactory factory = new SimpleTabViewFactory();
  private EditableActionAddableListView<ILibrary> libraryListView;
  private final ActionAddableListView<IMp3Track> mp3ListView;
  private final JButton searchButton = new JButton();
  private final MusicCategorizationView searchMusicCategorizationView = new MusicCategorizationView();
  private final JPanel searchParameterPanel = new JPanel(new GridDialogLayout(
      SearchSelectionComponent.getColumnCount(),
      false));
  private JPanel libraryPanel;
  private JPanel searchPanel;
  private final ILibraryControlProperties viewProperties;

  public LibraryControlView(ITableColumnViewSettings settings, ILibraryControlProperties properties) {
    this.viewProperties = properties;
    libraryListView = new EditableActionAddableListView<ILibrary>(
        viewProperties.getLibrariesString(),
        settings,
        ILibrary.class);
    mp3ListView = new ActionAddableListView<IMp3Track>(viewProperties.getNoContentString(), IMp3Track.class);
  }

  public void addLibraryListSelectionListener(ListSelectionListener listener) {
    libraryListView.addListSelectionListener(listener);
  }

  public ISearchComponent addSearchParameter(String labelString) {
    SearchSelectionComponent searchSelectionComponent = new SearchSelectionComponent(labelString);
    searchSelectionComponent.addTo(searchParameterPanel);
    return searchSelectionComponent;
  }

  private JPanel createLibraryListPanel() {
    JPanel libraryListPanel = new JPanel(new GridDialogLayout(1, false));
    libraryListPanel.add(libraryListView.getComponent(), GridDialogLayoutData.FILL_BOTH);
    return libraryListPanel;
  }

  private JComponent createMp3ListPanel() {
    return mp3ListView.getComponent();
  }

  public JComponent getComponent() {
    return content;
  }

  public IActionAddableListView<ILibrary> getLibraryView() {
    return libraryListView;
  }

  public IMusicCategorizationView getSearchMusicCategorizationView() {
    return searchMusicCategorizationView;
  }

  public Object getSelectedLibrary() {
    Object[] selectedItems = libraryListView.getSelectedItems();
    if (selectedItems.length == 0) {
      return null;
    }
    return selectedItems[0];
  }

  public IActionAddableListView<IMp3Track> getTrackListView() {
    return mp3ListView;
  }

  public void initGui() {
    content.setLayout(new GridDialogLayout(3, true));
    TabbedView leftTabbedView = new TabbedView(TabDirection.Up);
    if (libraryPanel != null) {
      leftTabbedView.addTab(factory.createTabView(libraryPanel), new ContentProperties(viewProperties.getLibrariesString()));
    }
    if (searchPanel != null) {
      leftTabbedView.addTab(factory.createTabView(searchPanel), new ContentProperties(viewProperties.getSearchString()));
    }
    GridDialogLayoutData tabbedPanelData = new GridDialogLayoutData(GridDialogLayoutData.FILL_BOTH);
    tabbedPanelData.setHorizontalSpan(2);
    content.add(leftTabbedView.getComponent(), tabbedPanelData);
    TabbedView rightTabbedView = new TabbedView(TabDirection.Up);
    rightTabbedView.addTab(factory.createTabView(createMp3ListPanel()), new ContentProperties(
        viewProperties.getTracksString()));
    content.add(rightTabbedView.getComponent(), GridDialogLayoutData.FILL_BOTH);
    content.setBorder(new TitledBorder(viewProperties.getLibraryControlBorderTitle()));
  }

  public void setSearchAction(SmartAction action) {
    searchButton.setAction(action);
  }

  public void addLibraryView() {
    libraryPanel = new JPanel(new GridDialogLayout(1, true));
    JPanel libraryListPanel = createLibraryListPanel();
    libraryPanel.add(libraryListPanel, GridDialogLayoutData.FILL_BOTH);
  }

  public void addSearchView(IMusicCategorizationProperties properties) {
    searchPanel = new JPanel(new GridDialogLayout(2, false));
    searchPanel.add(searchParameterPanel);
    searchPanel.add(searchButton);
    GridDialogLayoutData categorizationData = new GridDialogLayoutData(GridDialogLayoutData.FILL_BOTH);
    categorizationData.setHorizontalSpan(2);
    searchPanel.add(searchMusicCategorizationView.getContent(properties), categorizationData);
  }
}