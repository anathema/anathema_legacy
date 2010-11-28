package net.sf.anathema.character.craft.template;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.lib.util.Identificate;

public class CraftTemplate extends Identificate implements IGlobalAdditionalTemplate {

  public static final String ID = "Craft"; //$NON-NLS-1$

  public CraftTemplate() {
    super(ID);
  }

  public boolean supportsEdition(IExaltedEdition edition) {
    return true;
  }
}