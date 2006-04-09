package net.sf.anathema.character.impl.testing;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.xml.GenericCharacterTemplate;
import net.sf.anathema.character.generic.impl.template.AbstractCharacterTemplate;
import net.sf.anathema.character.generic.impl.template.essence.DefaultEssenceTemplate;
import net.sf.anathema.character.generic.impl.template.experience.DefaultExperienceCosts;
import net.sf.anathema.character.generic.impl.template.magic.DefaultMagicTemplate;
import net.sf.anathema.character.generic.impl.template.magic.SpellMagicTemplate;
import net.sf.anathema.character.generic.impl.template.points.DefaultBonusPointCosts;
import net.sf.anathema.character.generic.impl.template.presentation.MortalPresentationProperties;
import net.sf.anathema.character.generic.impl.traits.ExaltTraitTemplateFactory;
import net.sf.anathema.character.generic.impl.traits.TraitTemplateCollection;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.abilities.GenericAbilityUtilities;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.template.creation.IBonusPointCosts;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.type.CharacterType;

public class TestTemplate extends AbstractCharacterTemplate {

  private static final GenericCharacterTemplate MORTAL_TEMPLATE = new GenericCharacterTemplate();
  public static final ITemplateType TEMPLATE_TYPE = new TemplateType(CharacterType.SOLAR);
  private final IBonusPointCosts bonusCosts = new DefaultBonusPointCosts();
  private final IAdditionalRules additionalRules = new DummyAdditionalRules();
  private final ICreationPoints creationPoints = new TestCreationPoints();
  private final ITraitTemplateCollection traitTemplateCollection = new TraitTemplateCollection(
      new ExaltTraitTemplateFactory());
  private final DefaultMagicTemplate magicTemplate;

  public TestTemplate() {
    this(new DummyCharmTemplate());
  }

  public TestTemplate(ICharmTemplate charmTemplate) {
    ISpellMagicTemplate spellMagic = new SpellMagicTemplate(CircleType.getSorceryCircles(), new CircleType[] {
        CircleType.Shadowlands,
        CircleType.Labyrinth });
    this.magicTemplate = new DefaultMagicTemplate(charmTemplate, spellMagic);
  }

  public IMagicTemplate getMagicTemplate() {
    return magicTemplate;
  }

  public ITemplateType getTemplateType() {
    return new TemplateType(CharacterType.SOLAR);
  }

  @Override
  public IAdditionalRules getAdditionalRules() {
    return additionalRules;
  }

  public IEssenceTemplate getEssenceTemplate() {
    return new DefaultEssenceTemplate();
  }

  public IGroupedTraitType[] getAbilityGroups() {
    return GenericAbilityUtilities.createDefaultAbilityGroups();
  }

  public IExperiencePointCosts getExperienceCost() {
    return new DefaultExperienceCosts();
  }

  public IPresentationProperties getPresentationProperties() {
    return new MortalPresentationProperties(MORTAL_TEMPLATE.getTemplateType()) {
      @Override
      public String getNewActionResource() {
        return "CharacterGenerator.NewCharacter." + getCharacterTypeId() + ".Name"; //$NON-NLS-1$ //$NON-NLS-2$
      }
    };
  }

  @Override
  public ICasteType[] getAllCasteTypes() {
    return new ICasteType[0];
  }

  public IBonusPointCosts getBonusPointCosts() {
    return bonusCosts;
  }

  public ICreationPoints getCreationPoints() {
    return creationPoints;
  }

  public ITraitTemplateCollection getTraitTemplateCollection() {
    return traitTemplateCollection;
  }
}