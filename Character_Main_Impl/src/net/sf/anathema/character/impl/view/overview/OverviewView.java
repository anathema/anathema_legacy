package net.sf.anathema.character.impl.view.overview;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.character.view.overview.IOverviewView;
import net.sf.anathema.lib.gui.IView;

public class OverviewView implements IOverviewView, IView {

  private final Box panel = new Box(BoxLayout.Y_AXIS);
  private final JComponent content = new JScrollPane(
      panel,
      ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

  public JComponent getComponent() {
    return content;
  }

  public final IOverviewCategory addOverviewCategory(String borderText) {
    return new OverviewCategory(panel, borderText, true);
  }
}