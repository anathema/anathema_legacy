package net.sf.anathema.charmtree.filters;

import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.gui.ui.AbstractObjectUi;
import net.sf.anathema.lib.resources.Resources;

public class BookUI extends AbstractObjectUi<IExaltedSourceBook> {
  private final Resources resources;

  public BookUI(Resources resources) {
    this.resources = resources;
  }

  @Override
  public String getLabel(IExaltedSourceBook value) {
    return resources.getString("ExaltedSourceBook." + value.getId());
  }
}