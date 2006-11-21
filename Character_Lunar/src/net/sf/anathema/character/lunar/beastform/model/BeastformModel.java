package net.sf.anathema.character.lunar.beastform.model;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.GlobalCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.framework.util.HealthParameterUtilities;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.model.gift.GiftModel;
import net.sf.anathema.character.lunar.beastform.model.gift.GiftVisitorAdapter;
import net.sf.anathema.character.lunar.beastform.model.gift.IGift;
import net.sf.anathema.character.lunar.beastform.model.gift.IGiftModel;
import net.sf.anathema.character.lunar.beastform.model.gift.SoakProvidingGift;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformAttribute;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.lunar.template.ILunarSpecialCharms;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.intvalue.IntValueControl;

public class BeastformModel implements IBeastformModel {
  private final ICharacterModelContext context;
  private final IntValueControl charmLearnControl = new IntValueControl();
  private final IBeastformGroupCost cost;
  private final BeastformTraitCollection collection;
  private final IGiftModel giftModel;
  private final BeastformGenericTraitCollection allTraitsCollection;

  // Idee: Attributpunkte aus der Menschlichen Form andersfarbig darstellen. Gut?

  public BeastformModel(ICharacterModelContext context) {
    this.context = context;
    this.giftModel = new GiftModel(context, this);
    this.collection = new BeastformTraitCollection();
    this.cost = new BeastformGroupCost(collection, this);
    createAttributes();
    this.allTraitsCollection = new BeastformGenericTraitCollection(context.getTraitCollection(), collection, giftModel);
    context.getCharacterListening().addChangeListener(new GlobalCharacterChangeAdapter() {
      @Override
      public void characterChanged() {
        update();
      }
    });
    update();
  }

  private void createAttributes() {
    List<IBeastformAttribute> attributes = new ArrayList<IBeastformAttribute>();
    ITraitContext traitContext = context.getTraitContext();
    attributes.add(new BeastformAttribute(
        context.getTraitCollection().getTrait(AttributeType.Strength),
        traitContext,
        1,
        cost));
    attributes.add(new BeastformAttribute(
        context.getTraitCollection().getTrait(AttributeType.Dexterity),
        traitContext,
        2,
        cost));
    attributes.add(new BeastformAttribute(
        context.getTraitCollection().getTrait(AttributeType.Stamina),
        traitContext,
        1,
        cost));
    for (IBeastformAttribute attribute : attributes) {
      collection.addBeastFormAttribute(attribute);
    }
  }

  private void update() {
    fireCharmLearnCountChanged();
    for (IBeastformAttribute attribute : getAttributes()) {
      attribute.recalculate();
    }
  }

  public IBeastformAttribute[] getAttributes() {
    List<IBeastformAttribute> traits = new ArrayList<IBeastformAttribute>();
    for (AttributeType type : AttributeType.getAllFor(AttributeGroupType.Physical)) {
      traits.add(collection.getDeadlyBeastmanAttribute(type));
    }
    return traits.toArray(new IBeastformAttribute[traits.size()]);
  }

  private void fireCharmLearnCountChanged() {
    charmLearnControl.fireValueChangedEvent(getCharmValue());
  }

  public void setCharmLearnCount(int newValue) {
    context.getMagicCollection().setLearnCount(ILunarSpecialCharms.DEADLY_BEASTMAN_TRANSFORMATION, newValue);
  }

  public int getCharmValue() {
    return context.getMagicCollection().getLearnCount(ILunarSpecialCharms.DEADLY_BEASTMAN_TRANSFORMATION);
  }

  public void addCharmLearnCountChangedListener(IIntValueChangedListener listener) {
    charmLearnControl.addIntValueChangeListener(listener);
  }

  public String getTemplateId() {
    return BeastformTemplate.TEMPLATE_ID;
  }

  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Magic;
  }

  public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    return new NullAdditionalModelBonusPointCalculator();
  }

  public void addBonusPointsChangeListener(IChangeListener listener) {
    // Nothing to do
  }

  public IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return new NullAdditionalModelExperienceCalculator();
  }

  public IGiftModel getGiftModel() {
    return giftModel;
  }

  public IBeastformGroupCost getAttributeCostModel() {
    return cost;
  }

  public IBeastformAttribute getAttributeByType(AttributeType type) {
    return collection.getDeadlyBeastmanAttribute(type);
  }

  public IGenericTraitCollection getTraitCollection() {
    return allTraitsCollection;
  }

  public int getUncappedSoakValue(HealthType type) {
    Ensure.ensureTrue("Aggravated Soak not supported", type != HealthType.Aggravated); //$NON-NLS-1$
    int staminaValue = allTraitsCollection.getTrait(AttributeType.Stamina).getCurrentValue();
    final List<SoakProvidingGift> giftList = new ArrayList<SoakProvidingGift>();
    for (IQualitySelection<IGift> selection : giftModel.getSelectedQualities()) {
      selection.getQuality().accept(new GiftVisitorAdapter() {
        @Override
        public void acceptSoakProvidingGift(SoakProvidingGift gift) {
          gift.adjustActiveGiftList(giftList);
        }
      });
    }
    if (giftList.size() == 0) {
      return HealthParameterUtilities.getSoakValue(type, staminaValue);
    }
    float soakStaminaModifier = 0;
    for (SoakProvidingGift gift : giftList) {
      float currentStaminaModifier = gift.getSoakStaminaModifier(type);
      soakStaminaModifier = Math.max(soakStaminaModifier, currentStaminaModifier);
    }
    int soakValue = (int) Math.floor(staminaValue * soakStaminaModifier);
    for (SoakProvidingGift gift : giftList) {
      soakValue += gift.getBonus();
    }
    return soakValue;
  }

  public int getCurrentSoakValue(HealthType type) {
    return Math.min(getUncappedSoakValue(type), 12);
  }

  public int getHardnessValue(HealthType type) {
    int uncappedHardness = Math.max(getUncappedSoakValue(type) - 12, 0);
    return Math.min(uncappedHardness, 12);
  }
}