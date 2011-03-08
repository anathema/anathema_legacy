package net.sf.anathema.character.sidereal.generic;

import net.sf.anathema.character.generic.framework.magic.AbstractGenericCharm;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.ShortCharmTypeStringBuilder;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.magic.charms.duration.QualifiedAmountDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.lib.resources.IResources;

public class EssenceAuspicious extends AbstractGenericCharm {

  @Override
  protected String getId() {
    return "Sidereal.EssenceAuspicious"; //$NON-NLS-1$
  }

  @Override
  protected ExaltedSourceBook getSourceBook() {
    return ExaltedSourceBook.Sidereals2nd;
  }

  @Override
  protected boolean isComboOk() {
    return false;
  }

  public String getCostString(IResources resources) {
    return "1m+";
  }

  public String getDurationString(IResources resources) {
    return new QualifiedAmountDuration("1", "scene").getText(resources); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public String getType(IResources resources) {
    CharmTypeModel model = new CharmTypeModel();
    model.setCharmType(CharmType.Simple);
    return new ShortCharmTypeStringBuilder(resources).createTypeString(model);
  }
}