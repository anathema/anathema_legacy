package net.sf.anathema.character.lunar.heartsblood.persistence;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.lunar.heartsblood.presenter.IAnimalForm;
import net.sf.anathema.character.lunar.heartsblood.presenter.IHeartsBloodModel;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class HeartsBloodPersister implements IAdditionalPersister {

  private static final String TAG_HEARTS_BLOOD = "HeartsBlood"; //$NON-NLS-1$
  private static final String TAG_ANIMAL_FORM = "AnimalForm"; //$NON-NLS-1$
  private static final String ATTRIB_NAME = "name"; //$NON-NLS-1$
  private static final String ATTRIB_STRENGTH_VALUE = "animalStrength"; //$NON-NLS-1$
  private static final String ATTRIB_STAMINA_VALUE = "animalStamina"; //$NON-NLS-1$
  private static final String ATTRIB_DEXTERITY_VALUE = "animalDexterity"; //$NON-NLS-1$
  private static final String ATTRIB_APPEARANCE_VALUE = "animalAppearance"; //$NON-NLS-1$

  public void save(Element parent, IAdditionalModel model) {
    Element element = parent.addElement(TAG_HEARTS_BLOOD);
    IHeartsBloodModel heartsBloodModel = (IHeartsBloodModel) model;
    for (IAnimalForm form : heartsBloodModel.getEntries()) {
      saveAnimalForm(element, form);
    }
  }

  private void saveAnimalForm(Element element, IAnimalForm form) {
    Element animalFormElement = element.addElement(TAG_ANIMAL_FORM);
    animalFormElement.addAttribute(ATTRIB_NAME, form.getName());
    ElementUtilities.addAttribute(animalFormElement, ATTRIB_STRENGTH_VALUE, form.getStrength());
    ElementUtilities.addAttribute(animalFormElement, ATTRIB_STAMINA_VALUE, form.getStamina());
    if (form.getDexterity() > 0)
    	ElementUtilities.addAttribute(animalFormElement, ATTRIB_DEXTERITY_VALUE, form.getDexterity());
    if (form.getAppearance() > 0)
    	ElementUtilities.addAttribute(animalFormElement, ATTRIB_APPEARANCE_VALUE, form.getAppearance());
  }

  public void load(Element parent, IAdditionalModel model) throws PersistenceException {
    Element element = parent.element(TAG_HEARTS_BLOOD);
    IHeartsBloodModel heartsBloodModel = (IHeartsBloodModel) model;
    for (Element formElement : ElementUtilities.elements(element, TAG_ANIMAL_FORM)) {
      String name = ElementUtilities.getRequiredAttrib(formElement, ATTRIB_NAME);
      int strength = ElementUtilities.getRequiredIntAttrib(formElement, ATTRIB_STRENGTH_VALUE);
      int stamina = ElementUtilities.getRequiredIntAttrib(formElement, ATTRIB_STAMINA_VALUE);
      int dexterity = ElementUtilities.getIntAttrib(formElement, ATTRIB_DEXTERITY_VALUE, 0);
      int appearance = ElementUtilities.getIntAttrib(formElement, ATTRIB_APPEARANCE_VALUE, 0);
      heartsBloodModel.setCurrentName(name);
      heartsBloodModel.setCurrentStrength(strength);
      heartsBloodModel.setCurrentStamina(stamina);
      heartsBloodModel.setCurrentDexterity(dexterity);
      heartsBloodModel.setCurrentAppearance(appearance);
      heartsBloodModel.commitSelection();
    }
  }
}