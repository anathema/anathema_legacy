package net.sf.anathema.character.lunar.beastform.view;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;

import net.disy.commons.swing.layout.grid.EndOfLineMarkerComponent;
import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnView;
import net.sf.anathema.character.generic.framework.magic.view.MagicLearnView;
import net.sf.anathema.character.library.intvalue.MarkerIntValueDisplayFactory;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformView;
import net.sf.anathema.character.mutations.view.IMutationsView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

public class SecondEditionBeastformView implements IBeastformView
{
  private final JPanel spiritNamePanel = new JPanel(new GridDialogLayout(2, false));
  private final LineTextView spiritNameBox = new LineTextView(45);
  private final JPanel spiritAttributePanel = new JPanel(new GridDialogLayout(2, false));
  private final JPanel beastmanAttributePanel = new JPanel(new GridDialogLayout(2, false));
  private final JPanel giftPanel = new JPanel(new GridDialogLayout(4, false));
  private final MarkerIntValueDisplayFactory intValueDisplayFactory;
  private final JPanel content = new JPanel();
  private final JPanel overviewPanel = new JPanel();
  private final IBeastformViewProperties properties;

  public SecondEditionBeastformView(MarkerIntValueDisplayFactory intValueDisplayFactory, IBeastformViewProperties properties) {
    this.intValueDisplayFactory = intValueDisplayFactory;
    this.properties = properties;
  }

  public JComponent getComponent() {
	  JPanel spiritPanel = new JPanel(new GridDialogLayout(1, false));
	  
	  spiritPanel.add(spiritNamePanel);
	  spiritNameBox.setText(properties.getSpiritFormBoxInitialString());
	  spiritNamePanel.add(spiritNameBox.getComponent());
	  spiritNamePanel.setBorder(BorderFactory.createTitledBorder(properties.getSpiritFormBoxString()));

	  spiritAttributePanel.setBorder(new TitledBorder(properties.getAttributesString()));
	  spiritPanel.add(spiritAttributePanel);
	  spiritPanel.setBorder(BorderFactory.createTitledBorder(properties.getSpiritFormBoxString()));
	  
	  JPanel beastmanPanel = new JPanel();
	  beastmanPanel.setLayout(new BoxLayout(beastmanPanel, BoxLayout.Y_AXIS));
	  JPanel beastAttributePane = new JPanel(new GridDialogLayout(2,false));
	  beastAttributePane.add(beastmanAttributePanel);
	  beastAttributePane.add(new EndOfLineMarkerComponent());
	  
	  beastmanAttributePanel.setBorder(new TitledBorder(properties.getAttributesString()));
	  beastmanPanel.add(beastAttributePane);
	  giftPanel.setBorder(new TitledBorder(properties.getGiftsString()));
	  JPanel mutationPanel = new JPanel(new GridDialogLayout(2, false));
	  mutationPanel.add(giftPanel);
	  mutationPanel.add(overviewPanel, GridDialogLayoutDataFactory.createTopData());
	  beastmanPanel.add(mutationPanel);
	  beastmanPanel.setBorder(BorderFactory.createTitledBorder(properties.getDBTBoxString()));

	  content.add(spiritPanel);
	  content.add(beastmanPanel);
	  content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
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
  
  public void setSpiritListener(IObjectValueChangedListener<String> listener)
  {
	  spiritNameBox.addTextChangedListener(listener);
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

	@Override
	public void addMutationsView(IMutationsView mutationView) {
		giftPanel.add(mutationView.getComponent());
	}
}