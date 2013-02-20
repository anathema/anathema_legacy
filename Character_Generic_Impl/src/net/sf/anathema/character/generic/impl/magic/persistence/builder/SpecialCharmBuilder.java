package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.special.*;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import org.dom4j.Element;

public class SpecialCharmBuilder {
  public static final String ATTRIB_NAME = "name";
  public static final String ATTRIB_MODIFIER = "modifier";
  public static final String ATTRIB_TRAIT = "trait";
  public static final String ATTRIB_ESSENCE = "essence";

  public ISpecialCharm readSpecialCharm(Element charmElement, String id) {
    ISpecialCharm specialCharm = new OxBodyCharmBuilder().readOxBodyCharm(charmElement, id);
    specialCharm = specialCharm == null ? new PainToleranceCharmBuilder().readPainToleranceCharm(charmElement, id) : specialCharm;
    specialCharm = specialCharm == null ? new TraitCapModifierCharmBuilder().readTraitCapModifierCharm(charmElement, id) : specialCharm;
    specialCharm = specialCharm == null ? new TranscendenceCharmBuilder().readTranscendenceCharm(charmElement, id) : specialCharm;
    specialCharm = specialCharm == null ? new RepurchaseCharmBuilder().readRepurchaseCharm(charmElement, id) : specialCharm;
    specialCharm = specialCharm == null ? new EssenceFixedRepurchaseCharmBuilder().readEssenceFixedRepurchasesCharm(charmElement, id) : specialCharm;
    specialCharm = specialCharm == null ? new MultiEffectCharmBuilder().readMultiEffectCharm(charmElement, id) : specialCharm;
    specialCharm = specialCharm == null ? new UpgradableCharmBuilder().readUpgradableCharm(charmElement, id) : specialCharm;
    specialCharm = specialCharm == null ? new ElementalCharmBuilder().readElementalCharm(charmElement, id) : specialCharm;
    specialCharm = specialCharm == null ? new SubEffectCharmBuilder().readSubEffectCharm(charmElement, id) : specialCharm;
    return specialCharm;
  }
}