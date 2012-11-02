package net.sf.anathema.charmtree.filters;

import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.gui.ui.AbstractObjectUi;
import net.sf.anathema.lib.resources.IResources;

public class BookUI extends AbstractObjectUi<IExaltedSourceBook> {
  private final IResources resources;

  public BookUI(IResources resources) {
    this.resources = resources;
  }

  @Override
  public String getLabel(IExaltedSourceBook value) {
    return resources.getString("ExaltedSourceBook." + value.getId());
  }
}