package net.sf.anathema.character.main.xml.creation.template;

import net.sf.anathema.character.main.magic.model.charm.MartialArtsLevel;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class CharmCreationCostsTto implements Serializable {

  public CharmCreationCostGroupTto favored = new CharmCreationCostGroupTto();
  public CharmCreationCostGroupTto general = new CharmCreationCostGroupTto();
  public MartialArtsLevel standardMartialArtsLevel = MartialArtsLevel.Celestial;

  @Override
  public CharmCreationCostsTto clone() {
    return SerializationUtils.clone(this);
  }
}
