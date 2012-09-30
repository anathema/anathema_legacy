package net.sf.anathema.campaign.music.impl.view.categorization;

import net.miginfocom.swing.MigLayout;
import net.sf.anathema.campaign.music.presenter.IMusicEvent;
import net.sf.anathema.campaign.music.presenter.IMusicMood;
import net.sf.anathema.campaign.music.presenter.IMusicTheme;
import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationProperties;
import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationView;
import net.sf.anathema.campaign.music.presenter.ISelectionContainerView;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class MusicCategorizationView implements IMusicCategorizationView {

  private final SelectionContainerListView<IMusicEvent> eventsView = new SelectionContainerListView<IMusicEvent>(IMusicEvent.class);
  private final SelectionContainerListView<IMusicTheme> themesView = new SelectionContainerListView<IMusicTheme>(IMusicTheme.class);
  private final SelectionContainerListView<IMusicMood> moodsView = new SelectionContainerListView<IMusicMood>(IMusicMood.class);
  private JPanel content;

  public JComponent getContent(IMusicCategorizationProperties properties) {
    if (content == null) {
      content = createContent(properties);
    }
    return content;
  }

  private JPanel createContent(IMusicCategorizationProperties properties) {
    MigLayout layout = new MigLayout("", "[grow, fill][grow, fill][grow, fill]", "");
    JPanel musicCatagorizePanel = new JPanel(layout);
    musicCatagorizePanel.add(createListPanel(properties.getThemesString(), themesView.getComponent()));
    musicCatagorizePanel.add(createListPanel(properties.getEventsString(), eventsView.getComponent()));
    musicCatagorizePanel.add(createListPanel(properties.getMoodsString(), moodsView.getComponent()));
    return musicCatagorizePanel;
  }

  private JPanel createListPanel(String string, JList list) {
    JPanel panel = new JPanel(new MigLayout("", "0[grow, fill]0", "0[]0"));
    panel.setBorder(new TitledBorder(string));
    panel.add(new JScrollPane(list));
    return panel;
  }

  @Override
  public ISelectionContainerView<IMusicMood> getMoodsView() {
    return moodsView;
  }

  @Override
  public ISelectionContainerView<IMusicEvent> getEventsView() {
    return eventsView;
  }

  @Override
  public ISelectionContainerView<IMusicTheme> getThemesView() {
    return themesView;
  }
}