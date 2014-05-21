package net.sf.anathema.character.magic.description;

import net.sf.anathema.character.magic.basic.Magic;

public interface MagicDescriptionProvider {

  MagicDescription getCharmDescription(Magic magic);
}
