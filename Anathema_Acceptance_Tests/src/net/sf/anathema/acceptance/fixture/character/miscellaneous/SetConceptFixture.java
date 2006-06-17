package net.sf.anathema.acceptance.fixture.character.miscellaneous;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterRowEntryFixture;
import net.sf.anathema.acceptance.fixture.character.util.CasteAcceptanceUtilties;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;

public class SetConceptFixture extends AbstractCharacterRowEntryFixture {

  public String caste;

  @Override
  public void enterRow() throws Exception {
    ICasteType< ? extends ICasteTypeVisitor> type = caste == null ? null : getNonEmptyCaste();
    getCharacterStatistics().getCharacterConcept().getCaste().setType(type);
  }

  private ICasteType< ? extends ICasteTypeVisitor> getNonEmptyCaste() {
    return CasteAcceptanceUtilties.getNonEmptyCaste(getTemplate(), caste);
  }
}