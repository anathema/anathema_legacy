package net.sf.anathema.character.main.xml.magic;

import com.google.common.base.Predicate;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.template.magic.CharmTemplate;
import net.sf.anathema.character.main.template.magic.IMagicTemplate;
import net.sf.anathema.character.main.template.magic.ISpellMagicTemplate;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericMagicTemplate extends ReflectionCloneableObject<GenericMagicTemplate> implements IMagicTemplate {

  private Predicate<Magic> predicate;
  private CharmTemplate charmTemplate;
  private ISpellMagicTemplate spellTemplate;

  @Override
  public boolean canBuyFromFreePicks(Magic magic) {
    return predicate.apply(magic);
  }

  @Override
  public ISpellMagicTemplate getSpellMagic() {
    return spellTemplate;
  }

  @Override
  public CharmTemplate getCharmTemplate() {
    return charmTemplate;
  }

  public void setFreePicksPredicate(Predicate<Magic> predicate) {
    this.predicate = predicate;
  }

  public void setCharmTemplate(CharmTemplate template) {
    this.charmTemplate = template;
  }

  public void setSpellTemplate(ISpellMagicTemplate template) {
    this.spellTemplate = template;
  }
}