package net.sf.anathema.character.model.concept;

import net.sf.anathema.lib.util.Identificate;

public class NatureType extends Identificate implements INatureType {

  public NatureType(String id) {
    super(id);
  }
  
  // Note that the hashCode method included name and condition but equals did not.
}