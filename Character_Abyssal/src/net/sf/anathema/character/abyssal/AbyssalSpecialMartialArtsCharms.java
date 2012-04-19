package net.sf.anathema.character.abyssal;

import com.google.common.collect.Maps;
import net.sf.anathema.character.generic.impl.magic.charm.special.UpgradableCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.traits.ITraitType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class AbyssalSpecialMartialArtsCharms {

  public static ISpecialCharm FiveKnifeFist() {
    Map<String, Integer> bpCosts = newHashMap();
    bpCosts.put("BoneSpurs", 2);
    bpCosts.put("SkeletalProtrusions", 4);
    Map<String, Integer> xpCosts = newHashMap();
    xpCosts.put("BoneSpurs", 4);
    xpCosts.put("SkeletalProtrusions", 8);
    Map<String, Integer> essenceMins = newHashMap();
    essenceMins.put("SkeletalProtrusions", 4);
    Map<String, Integer> traitMins = newHashMap();
    Map<String, ITraitType> traits = newHashMap();
    return new UpgradableCharm("Abyssal.FiveKnifeFist", new String[]{"BoneSpurs","SkeletalProtrusions"}, true, bpCosts, xpCosts, essenceMins,
            traitMins, traits);
  }

  public static ISpecialCharm WrithingBloodChainTechnique() {
    Map<String, Integer> bpCosts = Collections.singletonMap("Taint", 6);
    Map<String, Integer> xpCosts = Collections.singletonMap("Taint", 12);
    Map<String, Integer> essenceMins = newHashMap();
    Map<String, Integer> traitMins = newHashMap();
    Map<String, ITraitType> traits = newHashMap();
    return new UpgradableCharm("Abyssal.WrithingBloodChainTechnique", new String[]{"Taint"}, true, bpCosts, xpCosts, essenceMins,
            traitMins, traits);
  }
}
