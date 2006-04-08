package net.sf.anathema.character.lunar.beastform.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledAlotmentView;

public class BeastformOverviewView implements IBeastformOverviewView {
  private LabelledAlotmentView attributeView;
  private LabelledAlotmentView giftView;
  private JPanel content;
  private final IBeastformOverviewViewProperties properties;

  public BeastformOverviewView(IBeastformOverviewViewProperties properties) {
    this.properties = properties;
  }

  public JComponent getContent() {
    if (content == null) {
      content = createContent();
    }
    return content;
  }

  private JPanel createContent() {
    JPanel overview = new JPanel(new GridDialogLayout(2, false));
    IGridDialogPanel panel = new DefaultGridDialogPanel();
    attributeView = new LabelledAlotmentView(properties.getAttributeDotsString(), 0, 0);
    attributeView.addComponents(panel);
    giftView = new LabelledAlotmentView(properties.getGiftPicksString(), 0, 0);
    giftView.addComponents(panel);
    overview.add(panel.getContent());
    return overview;
  }

  public ILabelledAlotmentView getAttributeOverview() {
    return attributeView;
  }

  public ILabelledAlotmentView getGiftOverview() {
    return giftView;
  }
}