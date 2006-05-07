package net.sf.anathema.charmentry.demo.model;

import net.sf.anathema.lib.util.IIdentificate;

public enum ExaltedSourceBooks implements IIdentificate {
  SecondEdition;

  public String getId() {
    return name();
  }
}