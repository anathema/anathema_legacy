package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.lib.collection.Predicate;

public class DefaultFreePicksPredicate extends Predicate<IMagic> {

  public boolean evaluate(IMagic magic) {
    return true;
  }
}