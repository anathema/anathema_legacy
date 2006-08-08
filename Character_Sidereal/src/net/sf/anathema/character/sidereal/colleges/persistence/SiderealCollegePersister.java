package net.sf.anathema.character.sidereal.colleges.persistence;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.library.trait.IFavorableModifiableTrait;
import net.sf.anathema.character.library.trait.persistence.AbstractCharacterPersister;
import net.sf.anathema.character.sidereal.colleges.model.CollegeType;
import net.sf.anathema.character.sidereal.colleges.model.SiderealCollegeModel;
import net.sf.anathema.character.sidereal.colleges.presenter.IAstrologicalHouse;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class SiderealCollegePersister extends AbstractCharacterPersister implements IAdditionalPersister {

  private static final String TAG_COLLEGES = "Colleges"; //$NON-NLS-1$

  public void save(Element parent, IAdditionalModel model) {
    Element collegesElement = parent.addElement(TAG_COLLEGES);
    SiderealCollegeModel collegeModel = (SiderealCollegeModel) model;
    for (IAstrologicalHouse house : collegeModel.getAllHouses()) {
      for (IFavorableModifiableTrait college : house.getColleges()) {
        saveTrait(collegesElement, college.getType().getId(), college);
      }
    }
  }

  public void load(Element parent, IAdditionalModel model) throws PersistenceException {
    Element collegesElement = parent.element(TAG_COLLEGES);
    SiderealCollegeModel collegeModel = (SiderealCollegeModel) model;
    for (Element collegeElement : ElementUtilities.elements(collegesElement)) {
      String collegeTypeId = collegeElement.getName();
      CollegeType collegeType = CollegeType.valueOf(collegeTypeId);
      restoreTrait(collegeElement, collegeModel.getCollege(collegeType));
    }
  }
}