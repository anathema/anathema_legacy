package net.sf.anathema.character.generic.impl.template.magic;

import com.google.common.base.Predicate;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;

public class CustomizableMagicTemplate implements IMagicTemplate {

  private final ICharmTemplate charmTemplate;
  private final ISpellMagicTemplate spellTemplate;
  private final Predicate<IMagic> freePicksPredicate;

  public CustomizableMagicTemplate(
          Predicate<IMagic> freePicksPredicate,
          ICharmTemplate charmTemplate,
          ISpellMagicTemplate spellTemplate) {
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

  @Override
  public FavoringTraitType getFavoringTraitType() {
    return FavoringTraitType.AbilityType;
  }
}