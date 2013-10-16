package net.sf.anathema.charmdatabase.presenter;

import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.lib.util.Identifier;

public class DirectUi extends AbstractUIConfiguration<Identifier> {

  @Override
  protected String labelForExistingValue(Identifier value) {
    return value.getId();
  }
}