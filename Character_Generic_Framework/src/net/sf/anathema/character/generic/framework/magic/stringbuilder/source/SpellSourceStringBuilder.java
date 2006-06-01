package net.sf.anathema.character.generic.framework.magic.stringbuilder.source;

import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.general.IMagicSource;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.lib.resources.IResources;

public class SpellSourceStringBuilder extends AbstractMagicSourceStringBuilder<ISpell> {

  private final IExaltedEdition edition;

  public SpellSourceStringBuilder(IResources resources, IExaltedEdition edition) {
    super(resources);
    this.edition = edition;
  }

  public String createSourceString(ISpell spell) {
    IMagicSource source = getSource(spell);
    return createSourceString(source).toString();
  }
  

  public String createShortSourceString(ISpell spell) {
    IMagicSource source = getSource(spell);
    return createShortSourceString(source).toString();
  }

  private IMagicSource getSource(ISpell spell) {
    return spell.getSource(edition);
  }
}