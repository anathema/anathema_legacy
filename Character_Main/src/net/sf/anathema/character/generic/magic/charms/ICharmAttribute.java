package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.lib.util.Identified;

public interface ICharmAttribute extends Identified {

  boolean isVisualized();
  
  String getValue();
}