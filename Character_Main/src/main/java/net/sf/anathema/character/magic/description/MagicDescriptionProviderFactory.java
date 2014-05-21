package net.sf.anathema.character.magic.description;

import net.sf.anathema.framework.IApplicationModel;

public interface MagicDescriptionProviderFactory {

  MagicDescriptionProvider create(IApplicationModel anathemaModel);
}
