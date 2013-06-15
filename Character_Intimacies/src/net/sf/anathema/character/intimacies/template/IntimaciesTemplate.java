package net.sf.anathema.character.intimacies.template;

import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class IntimaciesTemplate extends SimpleIdentifier implements IGlobalAdditionalTemplate {

  public static final String ID = "Intimacies";

  public IntimaciesTemplate() {
    super(ID);
  }
}