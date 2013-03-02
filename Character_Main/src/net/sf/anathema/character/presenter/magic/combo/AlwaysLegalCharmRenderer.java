package net.sf.anathema.character.presenter.magic.combo;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.ICharmInfoStringBuilder;
import net.sf.anathema.lib.resources.IResources;

public class AlwaysLegalCharmRenderer extends LegalityCharmRenderer {

  public AlwaysLegalCharmRenderer(IResources resources, ICharmInfoStringBuilder charmInfoStringProvider) {
    super(resources, charmInfoStringProvider);
  }

  @Override
  protected boolean isLegal(Object object) {
    return true;
  }
}