package net.sf.anathema.exaltedengine;

import net.sf.anathema.characterengine.DefaultEngine;
import net.sf.anathema.characterengine.DefaultPersona;
import net.sf.anathema.characterengine.DefaultQualities;
import net.sf.anathema.characterengine.Persona;

public class ExaltedEngine {
  public Persona createPersona() {
    return new DefaultPersona(new DefaultQualities(new DefaultEngine()));
  }
}