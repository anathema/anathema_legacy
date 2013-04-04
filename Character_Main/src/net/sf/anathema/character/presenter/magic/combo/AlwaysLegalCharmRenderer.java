package net.sf.anathema.character.presenter.magic.combo;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.ICharmInfoStringBuilder;
import net.sf.anathema.lib.resources.Resources;

public class AlwaysLegalCharmRenderer extends LegalityCharmRenderer {

  public AlwaysLegalCharmRenderer(Resources resources, ICharmInfoStringBuilder charmInfoStringProvider) {
    super(resources, charmInfoStringProvider);
  }

  @Override
  protected boolean isLegal(Object object) {
    return true;
  }
}