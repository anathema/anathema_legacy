package net.sf.anathema.hero.dummy.template;

import com.google.common.base.Predicate;
import net.sf.anathema.character.main.magic.basic.Magic;
import net.sf.anathema.character.main.template.magic.IMagicTemplate;
import net.sf.anathema.character.main.template.magic.ISpellMagicTemplate;

public class DummyMagicTemplate implements IMagicTemplate {

  private final ISpellMagicTemplate spellTemplate;

  public DummyMagicTemplate(ISpellMagicTemplate spellTemplate) {
    this.spellTemplate = spellTemplate;
  }

  @Override
  public ISpellMagicTemplate getSpellMagic() {
    return spellTemplate;
  }
}