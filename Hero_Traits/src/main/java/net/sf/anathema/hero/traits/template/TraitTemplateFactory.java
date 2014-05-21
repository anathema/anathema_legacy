package net.sf.anathema.hero.traits.template;

import net.sf.anathema.hero.traits.model.trait.ModificationType;

public class TraitTemplateFactory {

  public static TraitTemplate createEssenceLimitedTemplate(int minimumValue) {
    return createEssenceLimitedTemplate(minimumValue, minimumValue, ModificationType.RaiseOnly);
  }

  public static TraitTemplate createEssenceLimitedTemplate(int minimumValue, int startValue, ModificationType state) {
    LimitationTemplate limitation = new LimitationTemplate();
    limitation.type = LimitationType.Essence;
    return createTemplate(minimumValue, startValue, limitation, state);
  }

  public static TraitTemplate createStaticLimitedTemplate(int minimumValue, int staticLimit) {
    return createStaticLimitedTemplate(minimumValue, staticLimit, ModificationType.RaiseOnly);
  }

  public static TraitTemplate createStaticLimitedTemplate(int minimumValue, int staticLimit, ModificationType state) {
    LimitationTemplate limitation = new LimitationTemplate();
    limitation.type = LimitationType.Static;
    limitation.value = staticLimit;
    return createTemplate(minimumValue, minimumValue, limitation, state);
  }

  private static TraitTemplate createTemplate(int minimumValue, int startValue, LimitationTemplate limitation, ModificationType modificationType) {
    TraitTemplate template = new TraitTemplate();
    template.minimumValue = minimumValue;
    template.startValue = startValue;
    template.modificationType = modificationType;
    template.limitation = limitation;
    return template;
  }
}