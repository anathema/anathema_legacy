package net.sf.anathema.character.generic.template.additional;

import net.sf.anathema.character.generic.rules.IExaltedEdition;

public interface IGlobalAdditionalTemplate extends IAdditionalTemplate {

  boolean supportsEdition(IExaltedEdition edition);
}