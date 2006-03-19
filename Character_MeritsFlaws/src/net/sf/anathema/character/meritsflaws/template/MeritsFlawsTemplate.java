package net.sf.anathema.character.meritsflaws.template;

import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.lib.util.Identificate;

public class MeritsFlawsTemplate extends Identificate implements IAdditionalTemplate {

  public static final String ID = "MeritsAndFlaws"; //$NON-NLS-1$

  public MeritsFlawsTemplate() {
    super(ID);
  }

  public int getMaximalBonusPointGain() {
    return 10;
  }
}