package net.sf.anathema.character.main.magic.description;

import net.sf.anathema.character.main.magic.basic.Magic;

public interface MagicDescriptionProvider {

  MagicDescription getCharmDescription(Magic magic);
}
