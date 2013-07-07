package net.sf.anathema.character.main.testing.dummy.template;

import com.google.common.base.Predicate;
import net.sf.anathema.character.main.magic.IMagic;
import net.sf.anathema.character.main.template.magic.ICharmTemplate;
import net.sf.anathema.character.main.template.magic.IMagicTemplate;
import net.sf.anathema.character.main.template.magic.ISpellMagicTemplate;

public class DummyMagicTemplate implements IMagicTemplate {

  private final ICharmTemplate charmTemplate;
  private final ISpellMagicTemplate spellTemplate;
  private final Predicate<IMagic> freePicksPredicate;

  public DummyMagicTemplate(Predicate<IMagic> freePicksPredicate, ICharmTemplate charmTemplate, ISpellMagicTemplate spellTemplate) {
    this.freePicksPredicate = freePicksPredicate;
    this.charmTemplate = charmTemplate;
    this.spellTemplate = spellTemplate;
  }

  @Override
  public ISpellMagicTemplate getSpellMagic() {
    return spellTemplate;
  }

  @Override
  public ICharmTemplate getCharmTemplate() {
    return charmTemplate;
  }

  @Override
  public boolean canBuyFromFreePicks(IMagic magic) {
    return freePicksPredicate.apply(magic);
  }
}