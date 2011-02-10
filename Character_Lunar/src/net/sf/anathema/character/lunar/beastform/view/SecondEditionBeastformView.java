package net.sf.anathema.character.lunar.beastform.view;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.EndOfLineMarkerComponent;
import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnView;
import net.sf.anathema.character.generic.framework.magic.view.MagicLearnView;
import net.sf.anathema.character.library.intvalue.MarkerIntValueDisplayFactory;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformView;
import net.sf.anathema.framework.value.IIntValueView;

public class SecondEditionBeastformView implements IBeastformView {

  private final JPanel attributePanel = new JPanel(new GridDialogLayout(2, false));
  private final JPanel giftPanel = new JPanel(new GridDialogLayout(4, false));
  private final MarkerIntValueDisplayFactory intValueDisplayFactory;
  private final JPanel content = new JPanel(new GridDialogLayout(2, false));
  private final JPanel overviewPanel = new JPanel();
  private final IBeastformViewProperties properties;

  public SecondEditionBeastformView(MarkerIntValueDisplayFactory intValueDisplayFactory, IBeastformViewProperties properties) {
    this.intValueDisplayFactory = intValueDisplayFactory;
    this.properties = properties;
  }

  public JComponent getComponent() {
	  attributePanel.setBorder(new TitledBorder(properties.getAttributesString()));
	  GridDialogLayoutData data = GridDialogLayoutDataUtilities.createHorizontalSpanData(2);
	  data.setHorizontalAlignment(GridAlignment.FILL);
	  content.add(attributePanel, data);
	  content.add(new EndOfLineMarkerComponent());
	  giftPanel.setBorder(new TitledBorder(properties.getGiftsString()));
	  content.add(giftPanel);
	  content.add(overviewPanel, GridDialogLayoutDataUtilities.createTopData());
	  return content;
  }

  public IIntValueView addCharmValueView(String label, int value, int maxValue) {
    return null;
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
    learnView.addTo(giftPanel);
    return learnView;
  }

  public IBeastformOverviewView addOverviewView(IBeastformOverviewViewProperties overviewProperties) {
    return new SecondEditionBeastformOverviewView(overviewPanel, overviewProperties);
  }
}