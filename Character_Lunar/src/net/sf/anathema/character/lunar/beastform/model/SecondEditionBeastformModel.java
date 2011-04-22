package net.sf.anathema.character.lunar.beastform.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentPrintModel;
import net.sf.anathema.character.equipment.impl.character.model.print.EquipmentPrintModel;
import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.GlobalCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.model.gift.IGiftModel;
import net.sf.anathema.character.lunar.beastform.model.gift.SecondEditionGiftModel;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformAttribute;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.lunar.template.ILunarSpecialCharms;
import net.sf.anathema.lib.control.change.GlobalChangeAdapter;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.intvalue.IntValueControl;

public class SecondEditionBeastformModel extends AbstractAdditionalModelAdapter implements IBeastformModel {
  private final ICharacterModelContext context;
  private final IntValueControl charmLearnControl = new IntValueControl();
  private final IBeastformGroupCost cost;
  private final BeastformTraitCollection beastCollection;
  private final BeastformTraitCollection spiritCollection;
  private final IGiftModel giftModel;
  private final BeastformGenericTraitCollection allTraitsCollection;
  private final IEquipmentPrintModel equipmentModel;
  private String spiritForm = "";

  public SecondEditionBeastformModel(ICharacterModelContext context) {
    this.context = context;
    this.giftModel = new SecondEditionGiftModel(context, this);
    this.beastCollection = new BeastformTraitCollection();
    this.spiritCollection = new BeastformTraitCollection();
    this.cost = new BeastformGroupCost(beastCollection, this);
    createAttributes();
    this.allTraitsCollection = new BeastformGenericTraitCollection(context.getTraitCollection(), beastCollection, giftModel);
    
    IEquipmentAdditionalModel equipment = (IEquipmentAdditionalModel) context.getAdditionalModel(IEquipmentAdditionalModelTemplate.ID);
    this.equipmentModel = new EquipmentPrintModel(equipment, new BeastformNaturalSoak(allTraitsCollection, giftModel));
    context.getCharacterListening().addChangeListener(new GlobalCharacterChangeAdapter() {
      @Override
      public void characterChanged() {
        update();
      }
    });
    update();
  }
  
  public IEquipmentPrintModel getEquipmentModel() {
    return equipmentModel;
  }

  private void createAttributes() {
    List<IBeastformAttribute> attributes = new ArrayList<IBeastformAttribute>();
    ITraitContext traitContext = context.getTraitContext();
    attributes.add(new BeastformAttribute(
    	ExaltedEdition.SecondEdition,
        context.getTraitCollection().getTrait(AttributeType.Strength),
        traitContext,
        1,
        cost));
    attributes.add(new BeastformAttribute(
    	ExaltedEdition.SecondEdition,
        context.getTraitCollection().getTrait(AttributeType.Dexterity),
        traitContext,
        2,
        cost));
    attributes.add(new BeastformAttribute(
    	ExaltedEdition.SecondEdition,
        context.getTraitCollection().getTrait(AttributeType.Stamina),
        traitContext,
        1,
        cost));
    for (IBeastformAttribute attribute : attributes) {
    	beastCollection.addBeastFormAttribute(attribute);
    }
    
    attributes.clear();
    attributes.add(new SpiritFormAttribute(
        context.getTraitCollection().getTrait(AttributeType.Strength),
        context,
        traitContext));
    attributes.add(new SpiritFormAttribute(
        context.getTraitCollection().getTrait(AttributeType.Dexterity),
        context,
        traitContext));
    attributes.add(new SpiritFormAttribute(
        context.getTraitCollection().getTrait(AttributeType.Stamina),
        context,
        traitContext));
    attributes.add(new SpiritFormAttribute(
            context.getTraitCollection().getTrait(AttributeType.Appearance),
            context,
            traitContext));
    for (IBeastformAttribute attribute : attributes) {
    	spiritCollection.addBeastFormAttribute(attribute);
    }
  }

  private void update() {
    charmLearnControl.fireValueChangedEvent(getCharmValue());
    for (IBeastformAttribute attribute : getAttributes()) {
      attribute.recalculate();
    }
    for (IBeastformAttribute attribute : getSpiritAttributes()) {
        attribute.recalculate();
      }
  }

  public IBeastformAttribute[] getAttributes() {
    List<IBeastformAttribute> traits = new ArrayList<IBeastformAttribute>();
    for (AttributeType type : AttributeType.getAllFor(AttributeGroupType.Physical)) {
      traits.add(beastCollection.getDeadlyBeastmanAttribute(type));
    }
    return traits.toArray(new IBeastformAttribute[traits.size()]);
  }
  
  public String getSpiritForm()
  {
	  return spiritForm;
  }
  
  public void setSpiritForm(String newName)
  {
	  spiritForm = newName;
  }
  
  public IBeastformAttribute[] getSpiritAttributes() {
	    List<IBeastformAttribute> traits = new ArrayList<IBeastformAttribute>();
	    for (AttributeType type : AttributeType.getAllFor(AttributeGroupType.Physical)) {
	      traits.add(spiritCollection.getDeadlyBeastmanAttribute(type));
	    }
	    traits.add(spiritCollection.getDeadlyBeastmanAttribute(AttributeType.Appearance));
	    return traits.toArray(new IBeastformAttribute[traits.size()]);
	  }

  public void setCharmLearnCount(int newValue) {
    context.getMagicCollection().setLearnCount(ILunarSpecialCharms.DEADLY_BEASTMAN_TRANSFORMATION, newValue);
  }

  public int getCharmValue() {
	  for (ICharm charm : context.getCharmContext().getCharmConfiguration().getLearnedCharms())
		  if (charm.getId().equals("Lunar.DeadlyBeastmanTransformation"))
			  return 1;
	  return 0;
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

  public void addChangeListener(IChangeListener listener) {
    giftModel.addModelChangeListener(listener);
    for (IBeastformAttribute trait : getAttributes()) {
      trait.getTrait().addCurrentValueListener(new GlobalChangeAdapter<Object>(listener));
    }
    for (IBeastformAttribute trait : getSpiritAttributes()) {
        trait.getTrait().addCurrentValueListener(new GlobalChangeAdapter<Object>(listener));
      }
  }

  public IGiftModel getGiftModel() {
    return giftModel;
  }

  public IBeastformGroupCost getAttributeCostModel() {
    return cost;
  }

  public IBeastformAttribute getAttributeByType(AttributeType type) {
    return beastCollection.getDeadlyBeastmanAttribute(type);
  }
  
  public IBeastformAttribute getSpiritAttributeByType(AttributeType type) {
	    return spiritCollection.getDeadlyBeastmanAttribute(type);
	  }

  public IGenericTraitCollection getSpiritTraitCollection() {
	    return spiritCollection;
	  }
  
  public IGenericTraitCollection getBeastTraitCollection() {
    return allTraitsCollection;
  }
}