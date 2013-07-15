package net.sf.anathema.character.main.magic.description;

import net.sf.anathema.character.main.magic.model.magic.Magic;

public interface MagicDescriptionProvider {

  MagicDescription getCharmDescription(Magic magic);
}
