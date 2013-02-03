package net.sf.anathema.character.presenter.magic.combo;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.ICharmInfoStringBuilder;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.lib.resources.IResources;

public class ComboLegalityCharmRenderer extends LegalityCharmRenderer {

  private final IComboConfiguration comboConfiguration;

  public ComboLegalityCharmRenderer(IResources resource, ICharmInfoStringBuilder charmInfoStringBuilder,
                                    IComboConfiguration comboConfiguration) {
    super(resource, charmInfoStringBuilder);
    this.comboConfiguration = comboConfiguration;
  }

  @Override
  protected boolean isLegal(Object object) {
    return comboConfiguration.isComboLegal((ICharm) object);
  }
}
