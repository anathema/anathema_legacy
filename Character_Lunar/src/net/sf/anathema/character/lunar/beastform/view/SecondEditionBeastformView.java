package net.sf.anathema.character.lunar.beastform.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
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
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

public class SecondEditionBeastformView implements IBeastformView
{
  private final JPanel spiritNamePanel = new JPanel(new GridDialogLayout(2, false));
  private final LineTextView spiritNameBox = new LineTextView(45);
  private final JPanel spiritAttributePanel = new JPanel(new GridDialogLayout(2, false));
  private final JPanel beastmanAttributePanel = new JPanel(new GridDialogLayout(2, false));
  private final JPanel giftPanel = new JPanel(new GridDialogLayout(4, false));
  private final MarkerIntValueDisplayFactory intValueDisplayFactory;
  private final JPanel content = new JPanel(new GridDialogLayout(1, false));
  private final JPanel overviewPanel = new JPanel();
  private final IBeastformViewProperties properties;

  public SecondEditionBeastformView(MarkerIntValueDisplayFactory intValueDisplayFactory, IBeastformViewProperties properties) {
    this.intValueDisplayFactory = intValueDisplayFactory;
    this.properties = properties;
  }

  public JComponent getComponent() {
	  JPanel spiritPanel = new JPanel(new GridDialogLayout(1, false));
	  
	  /*spiritNameBox.getTextComponent().setDisabledTextColor(Color.DARK_GRAY);
	  LabelTextView labelView = new LabelTextView(properties.getSpiritFormBoxString(),
			  spiritNameBox);
	  labelView.setDisabledLabelColor(Color.DARK_GRAY);
	  labelView.addToStandardPanel(spiritNamePanel);

	  //spiritNameBox.setPreferredSize(new Dimension(100, 30));
	  //spiritNamePanel.add(spiritNameBox);
	  spiritNameBox.setText(properties.getSpiritFormBoxString());
	  spiritNamePanel.setBorder(BorderFactory.createTitledBorder(properties.getSpiritFormBoxString()));

	  spiritPanel.add(spiritNamePanel);*/
	  spiritAttributePanel.setBorder(new TitledBorder(properties.getAttributesString()));
	  spiritPanel.add(spiritAttributePanel);
	  spiritPanel.setBorder(BorderFactory.createTitledBorder(properties.getSpiritFormBoxString()));
	  
	  JPanel beastmanPanel = new JPanel(new GridDialogLayout(2, false));
	  beastmanAttributePanel.setBorder(new TitledBorder(properties.getAttributesString()));
	  GridDialogLayoutData data = GridDialogLayoutDataUtilities.createHorizontalSpanData(2);
	  data.setHorizontalAlignment(GridAlignment.BEGINNING);
	  beastmanPanel.add(beastmanAttributePanel, data);
	  beastmanPanel.add(new EndOfLineMarkerComponent());
	  giftPanel.setBorder(new TitledBorder(properties.getGiftsString()));
	  beastmanPanel.add(giftPanel);
	  beastmanPanel.add(overviewPanel, GridDialogLayoutDataUtilities.createTopData());
	  beastmanPanel.setBorder(BorderFactory.createTitledBorder(properties.getDBTBoxString()));
	  
	  content.add(spiritPanel);
	  content.add(beastmanPanel);
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
    traitView.addComponents(beastmanAttributePanel);
    return traitView;
  }
  
  public void setSpiritListener(ChangeListener listener)
  {
	  
  }
  
  public SimpleTraitView addSpiritAttributeValueView(String label, int value, int maxValue) {
	SimpleTraitView traitView = new SimpleTraitView(
	     intValueDisplayFactory,
	     label,
	     value,
	     maxValue,
	     GridAlignment.BEGINNING);
	 traitView.addComponents(spiritAttributePanel);
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