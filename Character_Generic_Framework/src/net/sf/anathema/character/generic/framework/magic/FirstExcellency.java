package net.sf.anathema.character.generic.framework.magic;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.ShortCharmTypeStringBuilder;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.magic.charm.type.ReflexiveSpecialsModel;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.resources.IResources;

public class FirstExcellency extends AbstractGenericCharm {

  private final String costString;
  private final ICharacterType characterType;
  private final ExaltedSourceBook book;

  public FirstExcellency(ICharacterType characterType, ExaltedSourceBook book, String costString) {
    this.characterType = characterType;
    this.book = book;
    this.costString = costString;
  }

  @Override
  protected ExaltedSourceBook getSourceBook() {
    return book;
  }

  public String getCostString(IResources resources) {
    return costString;
  }

  @Override
  protected String getId() {
    return characterType.getId() + ".1stExcellency"; //$NON-NLS-1$
  }

  @Override
  protected boolean isComboOk() {
    return true;
  }

  public String getDurationString(IResources resources) {
    return SimpleDuration.INSTANT_DURATION.getText(resources);
  }

  public String getType(IResources resources) {
    CharmTypeModel model = new CharmTypeModel();
    model.setCharmType(CharmType.Reflexive);
    model.setSpecialModel(new ReflexiveSpecialsModel(1, 2));
    return new ShortCharmTypeStringBuilder(resources).createTypeString(model);
  }
}