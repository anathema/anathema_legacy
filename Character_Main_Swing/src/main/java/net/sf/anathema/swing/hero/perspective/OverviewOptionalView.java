package net.sf.anathema.swing.hero.perspective;

import net.sf.anathema.framework.view.util.OptionalView;
import org.jdesktop.swingx.JXCollapsiblePane;

import java.awt.event.ActionEvent;

public class OverviewOptionalView implements OptionalView{
  private JXCollapsiblePane overviewPane;

  public OverviewOptionalView(JXCollapsiblePane overviewPane) {
    this.overviewPane = overviewPane;
  }

  @Override
  public void toggle() {
    overviewPane.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION).actionPerformed(new ActionEvent(this, 0, JXCollapsiblePane.TOGGLE_ACTION));
  }
}