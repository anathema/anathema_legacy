package net.sf.anathema.character.generic.framework.magic;

import java.text.MessageFormat;
import java.util.regex.Pattern;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.source.MagicSourceStringBuilder;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public abstract class AbstractGenericCharm implements IMagicStats {

  public String getSourceString(IResources resources) {
    return new MagicSourceStringBuilder<IMagic>(resources).createShortSourceString(getSourceBook(), getId());
  }

  protected abstract ExaltedSourceBook getSourceBook();

  public String[] getDetailStrings(IResources resources) {
	String description = resources.getString(getId() + ".Description");
	String cleanedDescription = description = MessageFormat.format(description,
			  new Object[] { resources.getString(getTraitType().getId()) });
    return new String[] { cleanedDescription }; //$NON-NLS-1$
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
  
  protected abstract FavoringTraitType getTraitType();
  
  public int compareTo(IMagicStats stats) {
    if (stats instanceof AbstractGenericCharm) {
      return this.getId().compareTo(stats.getName().getId());
    }
    else {
      return -1;
    }
  }
}