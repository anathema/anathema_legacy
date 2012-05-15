package net.sf.anathema.character.generic.magic.charms.duration;

import net.sf.anathema.lib.resources.IStringResourceHandler;

public interface IDuration {

  String getText(IStringResourceHandler resources);

  void accept(IDurationVisitor visitor);
}