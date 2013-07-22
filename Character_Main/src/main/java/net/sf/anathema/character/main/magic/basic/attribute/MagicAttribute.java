package net.sf.anathema.character.main.magic.basic.attribute;

import net.sf.anathema.lib.util.Identifier;

public interface MagicAttribute extends Identifier {

  boolean isVisualized();
  
  String getValue();
}