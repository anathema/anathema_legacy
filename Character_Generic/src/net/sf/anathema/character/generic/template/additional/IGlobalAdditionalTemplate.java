package net.sf.anathema.character.generic.template.additional;

import net.sf.anathema.character.generic.rules.IExaltedEdition;

public interface IGlobalAdditionalTemplate extends IAdditionalTemplate {

  public boolean supportsEdition(IExaltedEdition edition);
}