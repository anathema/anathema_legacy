package net.sf.anathema.character.generic.dummy.template;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.impl.additional.NullAdditionalRules;
import net.sf.anathema.character.generic.impl.template.essence.NullEssenceTemplate;
import net.sf.anathema.character.generic.impl.template.magic.CustomizableMagicTemplate;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.template.creation.IBonusPointCosts;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.util.Identificate;

import static net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate.createStaticLimitedTemplate;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleDummyCharacterTemplate implements ICharacterTemplate {

  private final String subtype;
  private final CharacterType type;
  private final IExaltedEdition edition;

  public SimpleDummyCharacterTemplate(CharacterType type, String subtype, IExaltedEdition edition) {
    this.type = type;
    this.subtype = subtype;
    this.edition = edition;
  }

  @Override
  public boolean isNpcOnly() {
    return false;
  }

  @Override
  public IGroupedTraitType[] getAbilityGroups() {
    return new IGroupedTraitType[0];
  }

  @Override
  public IGroupedTraitType[] getAttributeGroups() {
    return new IGroupedTraitType[0];
  }

  @Override
  public IGroupedTraitType[] getYoziGroups() {
    return new IGroupedTraitType[0];
  }

  @Override
  public IAdditionalRules getAdditionalRules() {
    return new NullAdditionalRules();
  }

  @Override
  public IBonusPointCosts getBonusPointCosts() {
    return null;
  }

  @Override
  public ICasteCollection getCasteCollection() {
    return null;
  }

  @Override
  public ICreationPoints getCreationPoints() {
    return new TestCreationPoints();
  }

  @Override
  public IEssenceTemplate getEssenceTemplate() {
    return new NullEssenceTemplate();
  }

  @Override
  public IExperiencePointCosts getExperienceCost() {
    return null;
  }

  @Override
  public IPresentationProperties getPresentationProperties() {
    return null;
  }

  @Override
  public ITemplateType getTemplateType() {
    if (subtype == null) {
      return new TemplateType(type);
    }
    return new TemplateType(type, new Identificate(subtype));
  }

  @Override
  public ITraitTemplateCollection getTraitTemplateCollection() {
    ITraitTemplateCollection collection = mock(ITraitTemplateCollection.class);
    when(collection.getTraitTemplate(isA(ITraitType.class))).thenReturn(createStaticLimitedTemplate(0, 5));
    return collection;
  }

  @Override
  public ITraitType[] getToughnessControllingTraitTypes() {
    return new ITraitType[0];
  }

  @Override
  public IAdditionalTemplate[] getAdditionalTemplates() {
    return new IAdditionalTemplate[0];
  }

  @Override
  public IMagicTemplate getMagicTemplate() {
    ICharmTemplate charmTemplate = new DummyCharmTemplate();
    return new CustomizableMagicTemplate(null, charmTemplate, null);
  }

  @Override
  public IExaltedEdition getEdition() {
    return edition;
  }

  @Override
  public String[] getBaseHealthProviders() {
    return new String[0];
  }
}