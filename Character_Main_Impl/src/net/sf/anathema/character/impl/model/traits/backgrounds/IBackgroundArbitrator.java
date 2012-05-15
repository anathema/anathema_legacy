package net.sf.anathema.character.impl.model.traits.backgrounds;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;

public interface IBackgroundArbitrator {

  boolean accepts(IBackgroundTemplate backgroundTemplate);

}
