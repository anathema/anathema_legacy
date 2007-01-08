package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;

public class DefaultMagicTemplate extends CustomizableMagicTemplate {
  public DefaultMagicTemplate(ICharmTemplate charmTemplate, ISpellMagicTemplate spellTemplate) {
    super(new CustomizableFreePicksPredicate(true), charmTemplate, spellTemplate);
  }
}