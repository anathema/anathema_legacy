package net.sf.anathema.lib.util;

public class LinkedIdentificate extends Identificate implements ILinkedIdentificate {

  private final String[] parentIds;

  public LinkedIdentificate(String id, String[] parentIds) {
    super(id);
    this.parentIds = parentIds;
  }

  public String[] getParentIDs() {
    return parentIds;
  }
}