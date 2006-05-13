package net.sf.anathema.charmentry.model;

import net.sf.anathema.lib.util.IIdentificate;

public enum ExaltedSourceBooks implements IIdentificate {
  SecondEdition, WondersLostAge;

  public String getId() {
    return name();
  }
}