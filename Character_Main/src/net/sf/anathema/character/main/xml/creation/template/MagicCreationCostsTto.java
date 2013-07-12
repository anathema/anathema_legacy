package net.sf.anathema.character.main.xml.creation.template;

import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class MagicCreationCostsTto implements Serializable {

  public MagicCreationCostGroupTto favored = new MagicCreationCostGroupTto();
  public MagicCreationCostGroupTto general = new MagicCreationCostGroupTto();
  public MartialArtsLevel standardMartialArtsLevel = MartialArtsLevel.Celestial;

  @Override
  public MagicCreationCostsTto clone() {
    return SerializationUtils.clone(this);
  }
}
