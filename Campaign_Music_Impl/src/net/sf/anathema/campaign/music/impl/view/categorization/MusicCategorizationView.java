package net.sf.anathema.campaign.music.impl.view.categorization;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationProperties;
import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationView;
import net.sf.anathema.lib.workflow.container.ISelectionContainerView;
import net.sf.anathema.lib.workflow.container.view.SelectionContainerListView;

public class MusicCategorizationView implements IMusicCategorizationView {

  private final SelectionContainerListView eventsView = new SelectionContainerListView();
  private final SelectionContainerListView themesView = new SelectionContainerListView();
  private final SelectionContainerListView moodsView = new SelectionContainerListView();
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
        createListPanel(properties.getThemesString(), themesView.getContent()),
        GridDialogLayoutData.FILL_BOTH);
    musicCatagorizePanel.add(
        createListPanel(properties.getEventsString(), eventsView.getContent()),
        GridDialogLayoutData.FILL_BOTH);
    musicCatagorizePanel.add(
        createListPanel(properties.getMoodsString(), moodsView.getContent()),
        GridDialogLayoutData.FILL_BOTH);
    return musicCatagorizePanel;
  }

  private JPanel createListPanel(String string, JList list) {
    JPanel panel = new JPanel(new GridDialogLayout(1, false));
    panel.setBorder(new TitledBorder(string));
    panel.add(new JScrollPane(list), GridDialogLayoutData.FILL_BOTH);
    return panel;
  }

  public ISelectionContainerView getMoodsView() {
    return moodsView;
  }

  public ISelectionContainerView getEventsView() {
    return eventsView;
  }

  public ISelectionContainerView getThemesView() {
    return themesView;
  }
}