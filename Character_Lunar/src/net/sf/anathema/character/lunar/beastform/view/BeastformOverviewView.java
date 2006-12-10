package net.sf.anathema.character.lunar.beastform.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledAlotmentView;

public class BeastformOverviewView implements IBeastformOverviewView, IView {
  private LabelledAlotmentView attributeView;
  private LabelledAlotmentView giftView;
  private JPanel content;
  private final IBeastformOverviewViewProperties properties;

  public BeastformOverviewView(IBeastformOverviewViewProperties properties) {
    this.properties = properties;
  }

  public JComponent getComponent() {
    if (content == null) {
      content = createContent();
    }
    return content;
  }

  private JPanel createContent() {
    JPanel panel = new JPanel(new GridDialogLayout(4, false));
    attributeView = new LabelledAlotmentView(properties.getAttributeDotsString(), 0, 0, 2);
    attributeView.addTo(panel);
    giftView = new LabelledAlotmentView(properties.getGiftPicksString(), 0, 0, 2);
    giftView.addTo(panel);
    return panel;
  }

  public ILabelledAlotmentView getAttributeOverview() {
    return attributeView;
  }

  public ILabelledAlotmentView getGiftOverview() {
    return giftView;
  }
}