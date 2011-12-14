package net.sf.anathema.character.mutations.view;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnView;
import net.sf.anathema.character.generic.framework.magic.view.MagicLearnView;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;

public class MutationsView implements IMutationsView
{
  private final JPanel giftPanel = new JPanel(new GridDialogLayout(4, false));
  private JPanel overviewPanel = new JPanel();
  private final JPanel contentPanel = new JPanel(new GridDialogLayout(2, false));
  private final IMutationsViewProperties properties;
  
  public MutationsView(IMutationsViewProperties properties)
  {
	  this.properties = properties;
  }

  public JComponent getComponent() {
	  giftPanel.setBorder(new TitledBorder(properties.getMutationsString()));
	  JPanel mutationPanel = new JPanel(new GridDialogLayout(2, false));
	  mutationPanel.add(giftPanel);
	  if (overviewPanel != null)
		  mutationPanel.add(overviewPanel, GridDialogLayoutDataFactory.createTopData());
	  contentPanel.add(mutationPanel);

	  return contentPanel;
  }

  
  public IMagicLearnView addMutationsView(final IMutationLearnViewProperties giftViewProperties) {
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

  @Override
  public IOverviewCategory createOverview(String borderLabel) {
	  return new OverviewCategory(overviewPanel, borderLabel, false);
  }
}