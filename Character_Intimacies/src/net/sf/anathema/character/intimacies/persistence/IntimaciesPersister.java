package net.sf.anathema.character.intimacies.persistence;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.intimacies.IIntimaciesAdditionalModel;
import net.sf.anathema.character.intimacies.model.IIntimacy;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesModel;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.framework.persistence.AbstractPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class IntimaciesPersister extends AbstractPersister implements IAdditionalPersister {

  private static final String TAG_INTIMACIES = "Intimacies"; //$NON-NLS-1$
  private static final String TAG_INTIMACY = "Intimacy"; //$NON-NLS-1$
  private static final String ATTRIB_NAME = "name"; //$NON-NLS-1$
  private static final String TAG_TRAIT = "Trait"; //$NON-NLS-1$
  private static final String ATTRIB_COMPLETE = "complete"; //$NON-NLS-1$
  private final TraitPersister traitPersister = new TraitPersister();

  public void save(Element parent, IAdditionalModel model) {
    Element element = parent.addElement(TAG_INTIMACIES);
    IIntimaciesModel intimaciesModel = ((IIntimaciesAdditionalModel) model).getIntimaciesModel();
    for (IIntimacy intimacy : intimaciesModel.getEntries()) {
      saveIntimacy(element, intimacy);
    }
  }

  private void saveIntimacy(Element element, IIntimacy intimacy) {
    Element intimacyElement = element.addElement(TAG_INTIMACY);
    intimacyElement.addAttribute(ATTRIB_NAME, intimacy.getName());
    ElementUtilities.addAttribute(intimacyElement, ATTRIB_COMPLETE, intimacy.isComplete());
    traitPersister.saveTrait(intimacyElement, TAG_TRAIT, intimacy.getTrait());
  }

  public void load(Element parent, IAdditionalModel model) throws PersistenceException {
    Element element = parent.element(TAG_INTIMACIES);
    IIntimaciesModel intimaciesModel = ((IIntimaciesAdditionalModel) model).getIntimaciesModel();
    for (Element intimacyElement : ElementUtilities.elements(element, TAG_INTIMACY)) {
      String name = ElementUtilities.getRequiredAttrib(intimacyElement, ATTRIB_NAME);
      intimaciesModel.setCurrentName(name);
      IIntimacy intimacy = intimaciesModel.commitSelection();
      traitPersister.restoreTrait(intimacyElement, TAG_TRAIT, intimacy.getTrait());
      boolean complete = ElementUtilities.getBooleanAttribute(intimacyElement, ATTRIB_COMPLETE, false);
      intimacy.setComplete(complete);
    }
  }
}