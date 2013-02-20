package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.special.*;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class AllSpecialCharmBuilder {

  private final List<SpecialCharmBuilder> builders = new ArrayList<>();

  public AllSpecialCharmBuilder() {
    builders.add(new OxBodyCharmBuilder());
    builders.add(new PainToleranceCharmBuilder());
    builders.add(new TraitCapModifierCharmBuilder());
    builders.add(new TranscendenceCharmBuilder());
    builders.add(new RepurchaseCharmBuilder());
    builders.add(new EssenceFixedRepurchaseCharmBuilder());
    builders.add(new MultiEffectCharmBuilder());
    builders.add(new UpgradableCharmBuilder());
    builders.add(new ElementalCharmBuilder());
    builders.add(new SubEffectCharmBuilder());
  }

  public ISpecialCharm readCharm(Element charmElement, String id) {
    for (SpecialCharmBuilder builder : builders) {
      if (!builder.willReadCharm(charmElement)) {
        continue;
      }
      return builder.readCharm(charmElement, id);
    }
    return null;
  }
}