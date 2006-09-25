package net.sf.anathema.campaign.music.impl.view.categorization;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.campaign.music.presenter.IMusicEvent;
import net.sf.anathema.campaign.music.presenter.IMusicMood;
import net.sf.anathema.campaign.music.presenter.IMusicTheme;
import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationProperties;
import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationView;
import net.sf.anathema.lib.workflow.container.ISelectionContainerView;
import net.sf.anathema.lib.workflow.container.view.SelectionContainerListView;

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
    JPanel musicCatagorizePanel = new JPanel(new GridDialogLayout(3, true));
    musicCatagorizePanel.add(
        createListPanel(properties.getThemesString(), themesView.getComponent()),
        GridDialogLayoutData.FILL_BOTH);
    musicCatagorizePanel.add(
        createListPanel(properties.getEventsString(), eventsView.getComponent()),
        GridDialogLayoutData.FILL_BOTH);
    musicCatagorizePanel.add(
        createListPanel(properties.getMoodsString(), moodsView.getComponent()),
        GridDialogLayoutData.FILL_BOTH);
    return musicCatagorizePanel;
  }

  private JPanel createListPanel(String string, JList list) {
    JPanel panel = new JPanel(new GridDialogLayout(1, false));
    panel.setBorder(new TitledBorder(string));
    panel.add(new JScrollPane(list), GridDialogLayoutData.FILL_BOTH);
    return panel;
  }

  public ISelectionContainerView<IMusicMood> getMoodsView() {
    return moodsView;
  }

  public ISelectionContainerView<IMusicEvent> getEventsView() {
    return eventsView;
  }

  public ISelectionContainerView<IMusicTheme> getThemesView() {
    return themesView;
  }
}