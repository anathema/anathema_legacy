package net.sf.anathema.character.impl.view.overview;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.character.view.overview.IOverviewView;

public class OverviewView implements IOverviewView {

  private final Box panel = new Box(BoxLayout.Y_AXIS);
  private final JComponent content = new JScrollPane(
      panel,
      ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
  private final List<IOverviewCategory> categories = new ArrayList<IOverviewCategory>();

  public JComponent getComponent() {
    return content;
  }

  public final IOverviewCategory addOverviewCategory(String borderText) {
    OverviewCategory category = new OverviewCategory(borderText, true);
    categories.add(category);
    return category;
  }

  protected final List<IOverviewCategory> getCategories() {
    return categories;
  }

  public void initGui() {
    for (IOverviewCategory category : getCategories()) {
      panel.add(category.getComponent());
    }
  }
}