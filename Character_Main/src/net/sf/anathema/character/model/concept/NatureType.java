package net.sf.anathema.character.model.concept;

import net.sf.anathema.lib.util.Identificate;

public class NatureType extends Identificate implements INatureType {

  private final String name;
  private final String condition;

  public NatureType(String id, String name, String condition) {
    super(id);
    this.name = name;
    this.condition = condition;
  }
  
  // Note that the hashCode method included name and condition but equals did not.
}