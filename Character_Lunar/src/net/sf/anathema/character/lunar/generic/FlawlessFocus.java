package net.sf.anathema.character.lunar.generic;

import net.sf.anathema.character.generic.framework.magic.AbstractGenericCharm;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.ShortCharmTypeStringBuilder;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.lib.resources.IResources;

public class FlawlessFocus extends AbstractGenericCharm {

  @Override
  protected String getId() {
    return "Lunar.FlawlessFocus"; //$NON-NLS-1$
  }

  @Override
  protected ExaltedSourceBook getSourceBook() {
    return ExaltedSourceBook.Lunars2nd;
  }

  @Override
  protected boolean isComboOk() {
    return false;
  }

  public String getCostString(IResources resources) {
    return "-";
  }

  public String getDurationString(IResources resources) {
    return SimpleDuration.PERMANENT_DURATION.getText(); 
  }

  public String getType(IResources resources) {
    CharmTypeModel model = new CharmTypeModel();
    model.setCharmType(CharmType.Simple);
    return new ShortCharmTypeStringBuilder(resources).createTypeString(model);
  }
}