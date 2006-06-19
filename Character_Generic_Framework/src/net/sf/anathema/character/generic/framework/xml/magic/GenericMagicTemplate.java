package net.sf.anathema.character.generic.framework.xml.magic;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.lib.collection.Predicate;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericMagicTemplate extends ReflectionCloneableObject<GenericMagicTemplate> implements IMagicTemplate {

  private Predicate<IMagic> predicate;
  private ICharmTemplate charmTemplate;
  private ISpellMagicTemplate spellTemplate;
  private FavoringTraitType favoringTraitType = FavoringTraitType.AbilityType;

  public boolean canBuyFromFreePicks(IMagic magic) {
    return predicate.evaluate(magic);
  }

  public ISpellMagicTemplate getSpellMagic() {
    return spellTemplate;
  }

  public ICharmTemplate getCharmTemplate() {
    return charmTemplate;
  }

  public FavoringTraitType getFavoringTraitType() {
    return favoringTraitType;
  }

  public void setFreePicksPredicate(Predicate<IMagic> predicate) {
    this.predicate = predicate;
  }

  public void setCharmTemplate(ICharmTemplate template) {
    this.charmTemplate = template;
  }

  public void setFavoringTraitType(FavoringTraitType favoringTraitType) {
    this.favoringTraitType = favoringTraitType;
  }

  public void setSpellTemplate(ISpellMagicTemplate template) {
    this.spellTemplate = template;
  }
}