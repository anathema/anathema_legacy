package net.sf.anathema.character.generic.framework.magic;

import net.sf.anathema.character.generic.framework.magic.AbstractGenericCharm;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.ShortCharmTypeStringBuilder;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.magic.charm.type.ReflexiveSpecialsModel;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.resources.IResources;

public class ThirdExcellency extends AbstractGenericCharm {

  private final String costString;
  private final ICharacterType type;
  private final ExaltedSourceBook book;

  public ThirdExcellency(ICharacterType type, String costString, ExaltedSourceBook book) {
    this.type = type;
    this.costString = costString;
    this.book = book;
  }

  @Override
  protected ExaltedSourceBook getSourceBook() {
    return book;
  }

  public String getType(IResources resources) {
    CharmTypeModel model = new CharmTypeModel();
    model.setCharmType(CharmType.Reflexive);
    model.setSpecialModel(new ReflexiveSpecialsModel(4, 6));
    return new ShortCharmTypeStringBuilder(resources).createTypeString(model);
  }

  public String getDurationString(IResources resources) {
    return SimpleDuration.INSTANT_DURATION.getText(resources);
  }

  @Override
  protected String getId() {
    return type.getId() + ".3rdExcellency"; //$NON-NLS-1$
  }

  @Override
  protected boolean isComboOk() {
    return true;
  }

  public String getCostString(IResources resources) {
    return costString;
  }
}