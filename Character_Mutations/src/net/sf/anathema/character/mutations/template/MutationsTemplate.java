package net.sf.anathema.character.mutations.template;

import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.lib.util.Identifier;

public class MutationsTemplate extends Identifier implements IGlobalAdditionalTemplate {

  public static final String ID = "Mutations"; //$NON-NLS-1$

  public MutationsTemplate() {
    super(ID);
  }

  public int getMaximalBonusPointGain() {
    return 10;
  }
}