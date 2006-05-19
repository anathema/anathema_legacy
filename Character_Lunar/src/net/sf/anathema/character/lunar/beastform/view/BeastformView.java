package net.sf.anathema.character.lunar.beastform.view;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;

import net.disy.commons.swing.layout.grid.EndOfLineMarkerComponent;
import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnView;
import net.sf.anathema.character.generic.framework.magic.view.MagicLearnView;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactory;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;

public class BeastformView implements IBeastformView {

  private final IGridDialogPanel baseContentPanel = new DefaultGridDialogPanel();
  private final IGridDialogPanel attributePanel = new DefaultGridDialogPanel();
  private final JPanel giftPanel = new JPanel(new GridDialogLayout(4, false));
  private final IntValueDisplayFactory intValueDisplayFactory;
  private final JPanel content = new JPanel(new GridDialogLayout(2, false));
  private final JPanel overviewPanel = new JPanel();
  private final IBeastformViewProperties properties;

  public BeastformView(IntValueDisplayFactory intValueDisplayFactory, IBeastformViewProperties properties) {
    this.intValueDisplayFactory = intValueDisplayFactory;
    this.properties = properties;
  }

  public JComponent getComponent() {
    JPanel charmPanel = baseContentPanel.getContent();
    charmPanel.setBorder(new TitledBorder(properties.getCharmString()));
    content.add(charmPanel);
    content.add(new EndOfLineMarkerComponent());
    JPanel panel = attributePanel.getContent();
    panel.setBorder(new TitledBorder(properties.getAttributesString()));
    GridDialogLayoutData data = new GridDialogLayoutData();
    data.setHorizontalSpan(2);
    content.add(panel, data);
    content.add(new EndOfLineMarkerComponent());
    giftPanel.setBorder(new TitledBorder(properties.getGiftsString()));
    content.add(giftPanel);
    overviewPanel.setBorder(new TitledBorder(properties.getOverviewString()));
    GridDialogLayoutData overviewData = new GridDialogLayoutData();
    overviewData.setVerticalAlignment(GridAlignment.BEGINNING);
    content.add(overviewPanel, overviewData);
    return content;
  }

  public boolean needsScrollbar() {
    return false;
  }

  public IIntValueView addIntValueView(String label, int value, int maxValue) {
    SimpleTraitView traitView = new SimpleTraitView(intValueDisplayFactory, label, value, maxValue);
    traitView.addComponents(baseContentPanel);
    return traitView;
  }

  public ITextView addTextArea(String label) {
    LabelTextView labelledView = new LabelTextView(label, new AreaTextView(10, 20));
    labelledView.addTo(baseContentPanel);
    return labelledView;
  }

  public SimpleTraitView addAttributeValueView(String label, int value, int maxValue) {
    SimpleTraitView traitView = new SimpleTraitView(
        intValueDisplayFactory,
        label,
        value,
        maxValue,
        GridAlignment.BEGINNING);
    traitView.addComponents(attributePanel);
    return traitView;
  }

  public IMagicLearnView addGiftsView(final IGiftLearnViewProperties giftViewProperties) {
    MagicLearnView learnView = new MagicLearnView() {
      @Override
      protected ListSelectionListener createLearnedListListener(final JButton button, final JList list) {
        return giftViewProperties.getRemoveButtonEnabledListener(button, list);
      }
    };
    learnView.init(giftViewProperties);
    learnView.addToGridDialogLayoutPanel(giftPanel, new JButton[0]);
    return learnView;
  }

  public IBeastformOverviewView addOverviewView(IBeastformOverviewViewProperties overviewProperties) {
    BeastformOverviewView overviewView = new BeastformOverviewView(overviewProperties);
    overviewPanel.add(overviewView.getContent());
    return overviewView;
  }
}