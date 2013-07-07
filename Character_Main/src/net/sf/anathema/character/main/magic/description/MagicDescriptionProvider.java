package net.sf.anathema.character.main.magic.description;

import net.sf.anathema.character.main.magic.model.magic.IMagic;

public interface MagicDescriptionProvider {

  MagicDescription getCharmDescription(IMagic magic);
}
