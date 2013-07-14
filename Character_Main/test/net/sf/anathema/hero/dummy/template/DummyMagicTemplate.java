package net.sf.anathema.hero.dummy.template;

import com.google.common.base.Predicate;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.template.magic.CharmTemplate;
import net.sf.anathema.character.main.template.magic.IMagicTemplate;
import net.sf.anathema.character.main.template.magic.ISpellMagicTemplate;

public class DummyMagicTemplate implements IMagicTemplate {

  private final CharmTemplate charmTemplate;
  private final ISpellMagicTemplate spellTemplate;
  private final Predicate<Magic> freePicksPredicate;

  public DummyMagicTemplate(Predicate<Magic> freePicksPredicate, CharmTemplate charmTemplate, ISpellMagicTemplate spellTemplate) {
    this.freePicksPredicate = freePicksPredicate;
    this.charmTemplate = charmTemplate;
    this.spellTemplate = spellTemplate;
  }

  @Override
  public ISpellMagicTemplate getSpellMagic() {
    return spellTemplate;
  }

  @Override
  public CharmTemplate getCharmTemplate() {
    return charmTemplate;
  }

  @Override
  public boolean canBuyFromFreePicks(Magic magic) {
    return freePicksPredicate.apply(magic);
  }
}