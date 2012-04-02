package net.sf.anathema.character.intimacies.template;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.lib.util.Identificate;

public class IntimaciesTemplate extends Identificate implements IGlobalAdditionalTemplate {

  public static final String ID = "Intimacies"; //$NON-NLS-1$

  public IntimaciesTemplate() {
    super(ID);
  }

  @Override
  public boolean supportsEdition(IExaltedEdition edition) {
    return true;
  }
}