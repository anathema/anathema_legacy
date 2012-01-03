package net.sf.anathema.acceptance.fixture.character.template;

import net.disy.commons.core.util.ArrayUtilities;

public class CheckCompulsiveCharmsFixture extends AbstractTemplateColumnFixture {
  public String id;

  public boolean isCompulsive() {
    String[] compulsiveCharmIDs = getTemplate().getAdditionalRules().getCompulsiveCharmIDs();
    return ArrayUtilities.containsValue(compulsiveCharmIDs, id);
  }
}