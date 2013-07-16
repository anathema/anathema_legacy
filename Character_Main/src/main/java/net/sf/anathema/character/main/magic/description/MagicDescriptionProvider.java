package net.sf.anathema.character.main.magic.description;

import net.sf.anathema.character.main.magic.model.Magic;

public interface MagicDescriptionProvider {

  MagicDescription getCharmDescription(Magic magic);
}
