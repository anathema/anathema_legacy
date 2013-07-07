package net.sf.anathema.character.main.magic.model.charm;

import net.sf.anathema.lib.util.Identifier;

public interface ICharmAttribute extends Identifier {

  boolean isVisualized();
  
  String getValue();
}