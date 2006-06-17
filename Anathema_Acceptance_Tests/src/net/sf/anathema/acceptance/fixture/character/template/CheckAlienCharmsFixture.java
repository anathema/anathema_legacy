package net.sf.anathema.acceptance.fixture.character.template;

import net.sf.anathema.acceptance.fixture.character.util.CasteAcceptanceUtilties;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;

public class CheckAlienCharmsFixture extends AbstractTemplateColumnFixture {

  public String caste;

  public boolean isAllowedAlienCharms() {
    ICasteType< ? extends ICasteTypeVisitor> casteType = CasteAcceptanceUtilties.getNonEmptyCaste(getTemplate(), caste);
    return getTemplate().getMagicTemplate().getCharmTemplate().isAllowedAlienCharms(casteType);
  }
}
