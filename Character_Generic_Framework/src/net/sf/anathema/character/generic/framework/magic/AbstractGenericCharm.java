package net.sf.anathema.character.generic.framework.magic;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.source.MagicSourceStringBuilder;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public abstract class AbstractGenericCharm implements IMagicStats {

  public String getSourceString(IResources resources) {
    return new MagicSourceStringBuilder<IMagic>(resources).createShortSourceString(
        ExaltedSourceBook.SecondEdition,
        getId());
  }

  public String[] getDetailKeys() {
    return new String[] { getId() + ".Description" }; //$NON-NLS-1$
  }

  public final String getGroupName(IResources resources) {
    return resources.getString("Generics"); //$NON-NLS-1$
  }

  public final IIdentificate getName() {
    return new Identificate(getId());
  }

  public final String getNameString(IResources resources) {
    return resources.getString(getId());
  }

  protected abstract String getId();

  protected abstract boolean isComboOk();
}