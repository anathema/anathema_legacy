package net.sf.anathema.character.generic.magic.description;

import net.sf.anathema.character.generic.magic.IMagic;

public interface CharmDescriptionProvider {

  CharmDescription getCharmDescription(IMagic magic);
}
