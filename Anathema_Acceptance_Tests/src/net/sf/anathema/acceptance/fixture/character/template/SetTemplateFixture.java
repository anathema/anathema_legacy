package net.sf.anathema.acceptance.fixture.character.template;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import fitnesse.fixtures.RowEntryFixture;

public class SetTemplateFixture extends RowEntryFixture {

  public String characterType;
  public String subtemplate;
  public String edition;

  @Override
  public void enterRow() throws Exception {
    @SuppressWarnings("unchecked")
    CharacterTemplateSummary templateSummary = new CharacterTemplateSummary(summary);
    ICharacterTemplate template = templateSummary.createTemplate(characterType, subtemplate, edition);
    templateSummary.setCharacterTemplate(template);
  }
}
