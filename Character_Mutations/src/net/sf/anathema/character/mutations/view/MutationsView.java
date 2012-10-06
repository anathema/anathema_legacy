package net.sf.anathema.character.mutations.view;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnView;
import net.sf.anathema.character.generic.framework.magic.view.MagicLearnView;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class MutationsView implements IMutationsView {
  private final JPanel giftPanel = new JPanel(new MigLayout(fillWithoutInsets()));
  private JPanel overviewPanel = new JPanel();
  private final JPanel contentPanel = new JPanel(new MigLayout(new LC().wrapAfter(2).fill()));

  @Override
  public JComponent getComponent() {
    contentPanel.add(giftPanel, new CC().grow().push());
    contentPanel.add(overviewPanel, new CC().alignY("top"));
    return contentPanel;
  }

  @Override
  public IMagicLearnView addMutationsView(final IMutationLearnViewProperties giftViewProperties) {
    MagicLearnView learnView = new MagicLearnView() {
      @Override
      protected ListSelectionListener createLearnedListListener(JButton button, JList list) {
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

  @Override
  public void hideOverview() {
    overviewPanel.removeAll();
  }
}