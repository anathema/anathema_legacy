package net.sf.anathema.hero.dummy.template;

import com.google.common.base.Predicate;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.template.magic.IMagicTemplate;
import net.sf.anathema.character.main.template.magic.ISpellMagicTemplate;

public class DummyMagicTemplate implements IMagicTemplate {

  private final ISpellMagicTemplate spellTemplate;
  private final Predicate<Magic> freePicksPredicate;

  public DummyMagicTemplate(Predicate<Magic> freePicksPredicate, ISpellMagicTemplate spellTemplate) {
    this.freePicksPredicate = freePicksPredicate;
    this.spellTemplate = spellTemplate;
  }

  @Override
  public ISpellMagicTemplate getSpellMagic() {
    return spellTemplate;
  }

  @Override
  public boolean canBuyFromFreePicks(Magic magic) {
    return freePicksPredicate.apply(magic);
  }
}