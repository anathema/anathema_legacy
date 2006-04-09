package net.sf.anathema.character.impl.view.overview;

import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.view.overview.IOverviewViewProperties;

public class CreationOverviewView extends AbstractOverviewView {

  public void initGui(IOverviewViewProperties properties) {
    for (IOverviewCategory category : getCategories()) {
      getPanel().add(category.getComponent());
    }
  }
}