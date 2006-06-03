package net.sf.anathema.character.generic.magic.charms.duration;

import net.sf.anathema.lib.resources.IResources;

public interface IDuration {

  public String getText(IResources resources);

  public void accept(IDurationVisitor visitor);
}