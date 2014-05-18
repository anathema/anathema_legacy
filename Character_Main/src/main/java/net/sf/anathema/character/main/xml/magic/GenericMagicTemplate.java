package net.sf.anathema.character.main.xml.magic;

import net.sf.anathema.character.main.template.magic.IMagicTemplate;
import net.sf.anathema.character.main.template.magic.ISpellMagicTemplate;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericMagicTemplate extends ReflectionCloneableObject<GenericMagicTemplate> implements IMagicTemplate {

  private ISpellMagicTemplate spellTemplate;

  @Override
  public ISpellMagicTemplate getSpellMagic() {
    return spellTemplate;
  }

  public void setSpellTemplate(ISpellMagicTemplate template) {
    this.spellTemplate = template;
  }
}