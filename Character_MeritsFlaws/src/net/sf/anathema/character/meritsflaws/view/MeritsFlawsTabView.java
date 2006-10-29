package net.sf.anathema.character.meritsflaws.view;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.anathema.character.meritsflaws.presenter.IMeritsFlawsViewProperties;
import net.sf.anathema.character.meritsflaws.presenter.view.IMeritsFlawsTabView;
import net.sf.anathema.character.meritsflaws.presenter.view.IPerkView;

public class MeritsFlawsTabView implements IMeritsFlawsTabView {

  private final IMeritsFlawsViewProperties properties;
  private final JPanel panel = new JPanel(new BorderLayout());

  public MeritsFlawsTabView(IMeritsFlawsViewProperties properties) {
    this.properties = properties;
  }

  public JComponent getComponent() {
    return panel;
  }

  public IPerkView addPerkView() {
    PerkView perkView = new PerkView(properties);
    panel.add(perkView.getComponent(), BorderLayout.CENTER);
    return perkView;
  }
}