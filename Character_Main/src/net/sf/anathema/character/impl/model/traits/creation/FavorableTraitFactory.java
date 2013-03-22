package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.aggregated.AggregatedTrait;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.rules.FavorableTraitRules;

import java.util.ArrayList;
import java.util.List;

public class FavorableTraitFactory {

  private final ITraitTemplateCollection templateCollection;
  private final IBasicCharacterData basicCharacterData;
  private final ICharacterListening characterListening;
  private final ITraitContext traitContext;
  private final IAdditionalTraitRules additionalRules;

  public FavorableTraitFactory(ITraitContext traitContext, ITraitTemplateCollection templateCollection,
                               IAdditionalTraitRules additionalRules, IBasicCharacterData basicCharacterData,
                               ICharacterListening characterListening) {
    this.traitContext = traitContext;
    this.templateCollection = templateCollection;
    this.additionalRules = additionalRules;
    this.basicCharacterData = basicCharacterData;
    this.characterListening = characterListening;
  }

  public IFavorableTrait[] createTraits(IIdentifiedCasteTraitTypeGroup group,
                                        IIncrementChecker favoredIncrementChecker) {
    List<IFavorableTrait> newTraits = new ArrayList<>();
    for (ITraitType type : group.getAllGroupTypes()) {
      ICasteType[] casteTypes = group.getTraitCasteTypes(type);
      IFavorableTrait trait = createTrait(type, casteTypes, favoredIncrementChecker);
      newTraits.add(trait);
    }
    return newTraits.toArray(new IFavorableTrait[newTraits.size()]);
  }

  private IFavorableTrait createTrait(ITraitType traitType, ICasteType[] casteTypes,
                                      IIncrementChecker favoredIncrementChecker) {
    ITraitTemplate traitTemplate = templateCollection.getTraitTemplate(traitType);
    ILimitationContext limitationContext = traitContext.getLimitationContext();
    FavorableTraitRules favorableTraitRules = new FavorableTraitRules(traitType, traitTemplate, limitationContext);
    IValueChangeChecker valueChecker = new AdditionRulesTraitValueChangeChecker(traitType, limitationContext,
            additionalRules);
    if (traitType == AbilityType.Craft) {
      return createCraft(casteTypes, favoredIncrementChecker, favorableTraitRules, valueChecker);
    }
    return new DefaultTrait(favorableTraitRules, casteTypes, traitContext, basicCharacterData, characterListening,
            valueChecker, favoredIncrementChecker);
  }

  private IFavorableTrait createCraft(ICasteType[] casteTypes, IIncrementChecker favoredIncrementChecker,
                                      FavorableTraitRules favorableTraitRules, IValueChangeChecker valueChecker) {
    String[] crafts = new String[]{"Air", "Earth", "Fire", "Water", "Wood"};
    return new AggregatedTrait(favorableTraitRules, basicCharacterData, characterListening, traitContext, valueChecker,
            casteTypes, favoredIncrementChecker, crafts);
  }
}