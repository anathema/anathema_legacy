package net.sf.anathema.character.reporting.pdf.content.stats.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharmStats extends AbstractCharmStats {

  protected final IGenericCharacter character;

  public CharmStats(ICharm charm, IGenericCharacter character) {
    super(charm);
    this.character = character;
  }

  @Override
  protected String[] getDetailKeys() {
    String[] detailKeys = super.getDetailKeys();
    List<String> details = new ArrayList<>();
    Collections.addAll(details, detailKeys);
    if (character.isSubeffectCharm(getMagic())) {
      for (String subeffectId : character.getLearnedEffects(getMagic())) {
        details.add(getMagic().getId() + ".Subeffects." + subeffectId);
      }
    }
    return details.toArray(new String[details.size()]);
  }

  @Override
  public String getNameString(Resources resources) {
    StringBuilder nameString = new StringBuilder();
    nameString.append(resources.getString(getMagic().getId()));
    int learnCount = character.getLearnCount(getMagic());
    if (learnCount > 1) {
      nameString.append(" (");
      nameString.append(learnCount);
      nameString.append("x)");
    }
    return nameString.toString();
  }
}