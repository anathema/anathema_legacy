package net.sf.anathema.campaign.music.impl.view.library;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.campaign.music.impl.view.SimpleTabViewFactory;
import net.sf.anathema.campaign.music.impl.view.categorization.MusicCategorizationView;
import net.sf.anathema.campaign.music.model.libary.ILibrary;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.presenter.ILibraryControlProperties;
import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationProperties;
import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationView;
import net.sf.anathema.campaign.music.view.library.ILibraryControlView;
import net.sf.anathema.campaign.music.view.search.ISearchComponent;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.framework.view.util.TabDirection;
import net.sf.anathema.framework.view.util.TabbedView;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.list.actionview.ActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.EditableActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;
import net.sf.anathema.lib.gui.widgets.HorizontalLine;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;

public class LibraryControlView implements ILibraryControlView, IView {

  private final JPanel content;
  private final SimpleTabViewFactory factory = new SimpleTabViewFactory();
  private final EditableActionAddableListView<ILibrary> libraryListView;
  private final ActionAddableListView<IMp3Track> mp3ListView;
  private final JButton searchButton = new JButton();
  private final MusicCategorizationView searchMusicCategorizationView = new MusicCategorizationView();
  private final JPanel searchParameterPanel = new JPanel(new MigLayout(new LC().wrapAfter(3)));
  private JPanel libraryPanel;
  private JPanel searchPanel;
  private final ILibraryControlProperties viewProperties;

  public LibraryControlView(ITableColumnViewSettings settings, ILibraryControlProperties properties, JPanel content) {
    this.viewProperties = properties;
    this.content = content;
    libraryListView = new EditableActionAddableListView<ILibrary>(null, settings, ILibrary.class);
    mp3ListView = new ActionAddableListView<IMp3Track>(viewProperties.getNoContentString(), IMp3Track.class);
  }

  @Override
  public void addLibraryListSelectionListener(ListSelectionListener listener) {
    libraryListView.addListSelectionListener(listener);
  }

  @Override
  public ISearchComponent addSearchParameter(String labelString) {
    SearchSelectionComponent searchSelectionComponent = new SearchSelectionComponent(labelString);
    searchSelectionComponent.addTo(searchParameterPanel);
    return searchSelectionComponent;
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public IActionAddableListView<ILibrary> getLibraryView() {
    return libraryListView;
  }

  @Override
  public IMusicCategorizationView getSearchMusicCategorizationView() {
    return searchMusicCategorizationView;
  }

  @Override
  public Object getSelectedLibrary() {
    Object[] selectedItems = libraryListView.getSelectedItems();
    if (selectedItems.length == 0) {
      return null;
    }
    return selectedItems[0];
  }

  @Override
  public IActionAddableListView<IMp3Track> getTrackListView() {
    return mp3ListView;
  }

  @Override
  public void initGui() {
    content.add(new JLabel(viewProperties.getLibraryControlBorderTitle()), new CC().split(2).spanX());
    content.add(new HorizontalLine(), new CC().grow());
    TabbedView leftTabbedView = new TabbedView(TabDirection.Up);
    if (libraryPanel != null) {
      leftTabbedView.addView(factory.createTabView(libraryPanel),
              new ContentProperties(viewProperties.getLibrariesString()));
    }
    if (searchPanel != null) {
      leftTabbedView.addView(factory.createTabView(searchPanel),
              new ContentProperties(viewProperties.getSearchString()));
    }
    content.add(leftTabbedView.getComponent(), new CC().spanX(2).grow());
    TabbedView rightTabbedView = new TabbedView(TabDirection.Up);
    rightTabbedView.addView(factory.createTabView(createMp3View()),
            new ContentProperties(viewProperties.getTracksString()));
    content.add(rightTabbedView.getComponent(), new CC().grow());
  }

  private JComponent createMp3View() {
    JPanel mp3Panel = new JPanel(new MigLayout(new LC().wrapAfter(1).fill()));
    JComponent mp3Component = mp3ListView.getComponent();
    mp3Panel.add(mp3Component, new CC().push().grow().span());
    return mp3Panel;
  }

  @Override
  public void setSearchAction(SmartAction action) {
    searchButton.setAction(action);
  }

  public void addLibraryView() {
    libraryPanel = new JPanel(new MigLayout(new LC().wrapAfter(1).fill()));
    JComponent libraryListPanel = libraryListView.getComponent();
    libraryPanel.add(libraryListPanel, new CC().push().grow().span());
  }

  public void addSearchView(IMusicCategorizationProperties properties) {
    searchPanel = new JPanel(new MigLayout(new LC().wrapAfter(2).fill().insets("4", "0", "0", "0")));
    searchPanel.add(searchParameterPanel);
    searchPanel.add(searchButton, new CC().pushX(600f));
    searchPanel.add(searchMusicCategorizationView.getContent(properties), new CC().spanX().grow().push());
  }
}