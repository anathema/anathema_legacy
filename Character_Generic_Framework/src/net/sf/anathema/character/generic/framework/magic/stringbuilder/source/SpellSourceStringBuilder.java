package net.sf.anathema.character.generic.framework.magic.stringbuilder.source;

import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.general.IMagicSource;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.lib.resources.IResources;

public class SpellSourceStringBuilder extends MagicSourceStringBuilder<ISpell> {

  private final IExaltedEdition edition;

  public SpellSourceStringBuilder(IResources resources, IExaltedEdition edition) {
    super(resources);
    this.edition = edition;
  }

  @Override
  protected IMagicSource getSource(ISpell spell) {
    return spell.getSource(edition);
  }
}