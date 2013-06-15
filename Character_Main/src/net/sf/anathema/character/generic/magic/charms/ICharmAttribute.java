package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.lib.util.Identifier;

public interface ICharmAttribute extends Identifier {

  boolean isVisualized();
  
  String getValue();
}