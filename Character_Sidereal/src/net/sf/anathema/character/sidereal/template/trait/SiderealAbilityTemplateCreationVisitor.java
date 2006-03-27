package net.sf.anathema.character.sidereal.template.trait;

import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.impl.traits.alternate.AlternateRequirementTraitTemplate;
import net.sf.anathema.character.generic.impl.traits.caste.CasteRangeTraitTemplate;
import net.sf.anathema.character.generic.impl.traits.caste.ICasteTraitMinimum;
import net.sf.anathema.character.generic.impl.traits.caste.StaticCasteMinimum;
import net.sf.anathema.character.generic.impl.traits.types.FirstEditionAbilityTypeVisitor;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.sidereal.caste.SiderealCaste;

public class SiderealAbilityTemplateCreationVisitor extends FirstEditionAbilityTypeVisitor {

  private final BattlesAbilityAlternative battlesAlternative = new BattlesAbilityAlternative();
  private final DefaultAbilityAlternative defaultAlternative = new DefaultAbilityAlternative();
  private final JourneysAbilityAlternative journeysAlternative = new JourneysAbilityAlternative();
  private final SerenityAbilityAlternative serenityAlternative = new SerenityAbilityAlternative();
  private ITraitTemplate template;

  private CasteRangeTraitTemplate createAbilityCasteRangeTemplate(
      ITraitTemplate defaultTemplate,
      ICasteTraitMinimum[] casteMinimums) {
    return new CasteRangeTraitTemplate(defaultTemplate, casteMinimums);
  }

  private ITraitTemplate createDefaultTemplate() {
    return SimpleTraitTemplate.createEssenceLimitedTemplate(0);
  }

  private CasteRangeTraitTemplate createStandardCasteTemplate(StaticCasteMinimum[] casteRanges) {
    return new CasteRangeTraitTemplate(createDefaultTemplate(), casteRanges);
  }

  public ITraitTemplate getTraitTemplate() {
    return template;
  }

  public void visitArchery() {
    AlternateRequirementTraitTemplate defaultTemplate = defaultAlternative.createArcheryTemplate();
    ICasteTraitMinimum[] casteRanges = battlesAlternative.createArcheryTraitRange();
    template = createAbilityCasteRangeTemplate(defaultTemplate, casteRanges);
  }

  public void visitAthletics() {
    StaticCasteMinimum[] casteRanges = new StaticCasteMinimum[] {
        new StaticCasteMinimum(SiderealCaste.Battles, 2),
        new StaticCasteMinimum(SiderealCaste.Journeys, 2),
        new StaticCasteMinimum(SiderealCaste.Endings, 2) };
    template = createStandardCasteTemplate(casteRanges);
  }

  public void visitAwareness() {
    ITraitTemplate defaultTemplate = SimpleTraitTemplate.createEssenceLimitedTemplate(2);
    StaticCasteMinimum[] casteRanges = new StaticCasteMinimum[] { new StaticCasteMinimum(SiderealCaste.Secrets, 3) };
    template = createAbilityCasteRangeTemplate(defaultTemplate, casteRanges);
  }

  public void visitBrawl() {
    this.template = createDefaultTemplate();
  }

  public void visitBureaucracy() {
    template = SimpleTraitTemplate.createEssenceLimitedTemplate(2);
  }

  public void visitCraft() {
    ICasteTraitMinimum[] serenity = serenityAlternative.createCraftTraitRange();
    this.template = createAbilityCasteRangeTemplate(createDefaultTemplate(), serenity);
  }

  public void visitDodge() {
    StaticCasteMinimum[] casteRanges = new StaticCasteMinimum[] {
        new StaticCasteMinimum(SiderealCaste.Battles, 2),
        new StaticCasteMinimum(SiderealCaste.Endings, 3) };
    template = createStandardCasteTemplate(casteRanges);
  }

  public void visitEndurance() {
    StaticCasteMinimum journeys = new StaticCasteMinimum(SiderealCaste.Journeys, 2);
    StaticCasteMinimum[] casteRanges = new StaticCasteMinimum[] { journeys };
    template = createStandardCasteTemplate(casteRanges);
  }

  public void visitInvestigation() {
    StaticCasteMinimum secrets = new StaticCasteMinimum(SiderealCaste.Secrets, 3);
    StaticCasteMinimum[] casteRanges = new StaticCasteMinimum[] { secrets };
    template = createStandardCasteTemplate(casteRanges);
  }

  public void visitLarceny() {
    StaticCasteMinimum secrets = new StaticCasteMinimum(SiderealCaste.Secrets, 3);
    StaticCasteMinimum[] casteRanges = new StaticCasteMinimum[] { secrets };
    template = createStandardCasteTemplate(casteRanges);
  }

  public void visitLinguistics() {
    ITraitTemplate defaultTemplate = SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    StaticCasteMinimum[] casteRanges = new StaticCasteMinimum[] {
        new StaticCasteMinimum(SiderealCaste.Journeys, 2),
        new StaticCasteMinimum(SiderealCaste.Serenity, 3) };
    template = createAbilityCasteRangeTemplate(defaultTemplate, casteRanges);
  }

  public void visitLore() {
    template = SimpleTraitTemplate.createEssenceLimitedTemplate(3);
  }

  public void visitMartialArts() {
    ITraitTemplate defaultTemplate = SimpleTraitTemplate.createEssenceLimitedTemplate(2);
    StaticCasteMinimum endings = new StaticCasteMinimum(SiderealCaste.Endings, 3);
    StaticCasteMinimum[] casteRanges = new StaticCasteMinimum[] { endings };
    template = createAbilityCasteRangeTemplate(defaultTemplate, casteRanges);
  }

  public void visitMedicine() {
    StaticCasteMinimum serenity = new StaticCasteMinimum(SiderealCaste.Serenity, 2);
    StaticCasteMinimum[] casteRanges = new StaticCasteMinimum[] { serenity };
    template = createStandardCasteTemplate(casteRanges);
  }

  public void visitMelee() {
    AlternateRequirementTraitTemplate defaultTemplate = defaultAlternative.createMeleeTemplate();
    ICasteTraitMinimum[] casteRanges = battlesAlternative.createMeleeTraitRange();
    template = createAbilityCasteRangeTemplate(defaultTemplate, casteRanges);
  }

  public void visitOccult() {
    template = SimpleTraitTemplate.createEssenceLimitedTemplate(2);
  }

  public void visitPerformance() {
    ICasteTraitMinimum[] serenity = serenityAlternative.createPerformanceTraitRange();
    template = createAbilityCasteRangeTemplate(createDefaultTemplate(), serenity);
  }

  public void visitPresence() {
    StaticCasteMinimum[] casteRanges = new StaticCasteMinimum[] {
        new StaticCasteMinimum(SiderealCaste.Battles, 2),
        new StaticCasteMinimum(SiderealCaste.Serenity, 2) };
    template = createStandardCasteTemplate(casteRanges);
  }

  public void visitResistance() {
    StaticCasteMinimum battles = new StaticCasteMinimum(SiderealCaste.Battles, 2);
    StaticCasteMinimum[] casteRanges = new StaticCasteMinimum[] { battles };
    template = createStandardCasteTemplate(casteRanges);
  }

  public void visitRide() {
    ICasteTraitMinimum[] casteRanges = journeysAlternative.createRideTraitRange();
    template = createAbilityCasteRangeTemplate(createDefaultTemplate(), casteRanges);
  }

  public void visitSail() {
    ICasteTraitMinimum[] casteRanges = journeysAlternative.createSailTraitRange();
    template = createAbilityCasteRangeTemplate(createDefaultTemplate(), casteRanges);
  }

  public void visitSocialize() {
    ITraitTemplate defaultTemplate = SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    StaticCasteMinimum[] casteRanges = new StaticCasteMinimum[] {
        new StaticCasteMinimum(SiderealCaste.Secrets, 2),
        new StaticCasteMinimum(SiderealCaste.Serenity, 3) };
    template = createAbilityCasteRangeTemplate(defaultTemplate, casteRanges);
  }

  public void visitStealth() {
    ITraitTemplate defaultTemplate = SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    StaticCasteMinimum[] casteRanges = new StaticCasteMinimum[] {
        new StaticCasteMinimum(SiderealCaste.Secrets, 3),
        new StaticCasteMinimum(SiderealCaste.Endings, 3) };
    template = createAbilityCasteRangeTemplate(defaultTemplate, casteRanges);
  }

  public void visitSurvival() {
    StaticCasteMinimum journeys = new StaticCasteMinimum(SiderealCaste.Journeys, 2);
    StaticCasteMinimum[] casteRanges = new StaticCasteMinimum[] { journeys };
    template = createStandardCasteTemplate(casteRanges);
  }

  public void visitThrown() {
    template = defaultAlternative.createThrownTemplate();
  }
}