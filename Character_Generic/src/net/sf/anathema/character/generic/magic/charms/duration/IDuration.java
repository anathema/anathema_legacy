package net.sf.anathema.character.generic.magic.charms.duration;

import net.sf.anathema.lib.resources.IStringResourceHandler;

public interface IDuration {

  public String getText(IStringResourceHandler resources);

  public void accept(IDurationVisitor visitor);
}