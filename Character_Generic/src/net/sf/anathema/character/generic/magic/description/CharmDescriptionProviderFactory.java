package net.sf.anathema.character.generic.magic.description;

import net.sf.anathema.framework.IAnathemaModel;

public interface CharmDescriptionProviderFactory {

  CharmDescriptionProvider create(IAnathemaModel anathemaModel);
}
