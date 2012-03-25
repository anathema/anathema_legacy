package net.sf.anathema.character.generic.impl.bootjob;

import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.lib.lang.ITransformer;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class TemplateTransformer implements ITransformer<String, String> {

  private final Map<String, String> mapping = new HashMap<String, String>() {{
    put("RevisedLoyalAbyssal", TemplateType.DEFAULT_SUB_TYPE.getId());
    put("RevisedRenegadeAbyssal", "RenegadeAbyssal");
    put("CherakRevised", "Cherak");
    put("DreamsRevisedSubtype", "DreamsSubtype");
    put("DynasticRevisedSubtype", TemplateType.DEFAULT_SUB_TYPE.getId());
    put("ImmaculateRevisedSubtype", "ImmaculateSubtype");
    put("LookshyRevisedSubtype", "LookshySubtype");
    put("LookshyOutcasteRevisedSubtype", "LookshyOutcasteSubtype");
    put("LookshyRealmRevisedSubtype", "LookshyRealmSubtype");
    put("ThresholdOutcasteRevisedSubtype", "ThresholdOutcasteSubtype");
    put("RevisedInfernal", TemplateType.DEFAULT_SUB_TYPE.getId());
    put("CastelessRevised", "Casteless");
    put("DreamsRevised", "Dreams");
    put("SilverPactRevised", TemplateType.DEFAULT_SUB_TYPE.getId());
    put("RevisedDreams", "Dreams");
    put("RevisedRonin", "Ronin");
    put("Revised", TemplateType.DEFAULT_SUB_TYPE.getId());
    put("RevisedSolar", TemplateType.DEFAULT_SUB_TYPE.getId());
    put("DreamsRevisedEstablished", "DreamsEstablished");
    put("DreamsRevisedInfluential", "DreamsInfluential");
    put("DreamsRevisedLegendary", "DreamsLegendary");
    put("DreamsDawn", "Dreams");
    put("DreamsDawnRevised", "Dreams");
    put("DreamsZenith", "Dreams");
    put("DreamsZenithRevised", "Dreams");
    put("DreamsTwilight", "Dreams");
    put("DreamsTwilightRevised", "Dreams");
    put("DreamsNight", "Dreams");
    put("DreamsNightRevised", "Dreams");
    put("DreamsEclipse", "Dreams");
    put("DreamsEclipseRevised", "Dreams");
  }};

  @Override
  public String transform(String input) {
    for (Map.Entry<String, String> entry : mapping.entrySet()) {
      String replacee = getPatternForReplacement(entry.getKey());
      String replacement = getPatternForReplacement(entry.getValue());
      input = input.replaceFirst(replacee, replacement);
    }
    return input;
  }

  private String getPatternForReplacement(String template) {
    return MessageFormat.format("<CharacterType subtype=\"{0}\">", template);
  }
}