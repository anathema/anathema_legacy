package net.sf.anathema.acceptance.fixture.character.template;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import fit.ColumnFixture;

public abstract class AbstractTemplateColumnFixture extends ColumnFixture {

  protected ICharacterTemplate getTemplate() {
    return new CharacterTemplateSummary(summary).getTemplate();
  }
}
