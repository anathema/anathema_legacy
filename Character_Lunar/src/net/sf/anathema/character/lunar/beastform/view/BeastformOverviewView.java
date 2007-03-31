package net.sf.anathema.character.lunar.beastform.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;

public class BeastformOverviewView implements IBeastformOverviewView, IView {
  private final OverviewCategory category;
  private ILabelledAlotmentView attributeView;
  private ILabelledAlotmentView giftView;
  private final JPanel content = new JPanel();

  public BeastformOverviewView(IBeastformOverviewViewProperties properties) {
    this.category = new OverviewCategory(content, properties.getOverviewBorderString(), false);
    attributeView = category.addAlotmentView(properties.getAttributeDotsString(), 2);
    giftView = category.addAlotmentView(properties.getGiftPicksString(), 2);
  }

  public JComponent getComponent() {
    return content;
  }

  public ILabelledAlotmentView getAttributeOverview() {
    return attributeView;
  }

  public ILabelledAlotmentView getGiftOverview() {
    return giftView;
  }
}