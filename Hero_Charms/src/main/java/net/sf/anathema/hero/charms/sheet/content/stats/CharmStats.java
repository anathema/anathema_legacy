package net.sf.anathema.hero.charms.sheet.content.stats;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.hero.charms.sheet.content.CharmContentHelper;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharmStats extends AbstractCharmStats {

  protected final CharmContentHelper content;

  public CharmStats(Charm charm, CharmContentHelper content) {
    super(charm);
    this.content = content;
  }

  @Override
  protected String[] getDetailKeys() {
    String[] detailKeys = super.getDetailKeys();
    List<String> details = new ArrayList<>();
    Collections.addAll(details, detailKeys);
    if (content.isSubEffectCharm(getMagic())) {
      for (String subeffectId : content.getLearnedEffects(getMagic())) {
        details.add(getMagic().getId() + ".Subeffects." + subeffectId);
      }
    }
    return details.toArray(new String[details.size()]);
  }

  @Override
  public String getNameString(Resources resources) {
    StringBuilder nameString = new StringBuilder();
    nameString.append(resources.getString(getMagic().getId()));
    int learnCount = content.getLearnCount(getMagic());
    if (learnCount > 1) {
      nameString.append(" (");
      nameString.append(learnCount);
      nameString.append("x)");
    }
    return nameString.toString();
  }
}