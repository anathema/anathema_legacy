package net.sf.anathema.charmdatabase.presenter;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.lib.util.Identifier;

public class TypeUi extends AbstractUIConfiguration<Identifier> {
  private Resources resources;

  public TypeUi(Resources resources) {
    this.resources = resources;
  }

  @Override
  protected String labelForExistingValue(Identifier value) {
    return resources.getString(value.getId());
  }
}