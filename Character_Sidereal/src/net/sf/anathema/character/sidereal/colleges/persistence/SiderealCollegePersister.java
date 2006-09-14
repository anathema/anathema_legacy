package net.sf.anathema.character.sidereal.colleges.persistence;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.library.trait.IFavorableDefaultTrait;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.sidereal.colleges.model.CollegeType;
import net.sf.anathema.character.sidereal.colleges.model.SiderealCollegeModel;
import net.sf.anathema.character.sidereal.colleges.presenter.IAstrologicalHouse;
import net.sf.anathema.framework.persistence.AbstractPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class SiderealCollegePersister extends AbstractPersister implements IAdditionalPersister {

  private static final String TAG_COLLEGES = "Colleges"; //$NON-NLS-1$
  private final TraitPersister traitPersister = new TraitPersister();

  public void save(Element parent, IAdditionalModel model) {
    Element collegesElement = parent.addElement(TAG_COLLEGES);
    SiderealCollegeModel collegeModel = (SiderealCollegeModel) model;
    for (IAstrologicalHouse house : collegeModel.getAllHouses()) {
      for (IFavorableDefaultTrait college : house.getColleges()) {
        traitPersister.saveTrait(collegesElement, college.getType().getId(), college);
      }
    }
  }

  public void load(Element parent, IAdditionalModel model) throws PersistenceException {
    Element collegesElement = parent.element(TAG_COLLEGES);
    SiderealCollegeModel collegeModel = (SiderealCollegeModel) model;
    for (Element collegeElement : ElementUtilities.elements(collegesElement)) {
      String collegeTypeId = collegeElement.getName();
      CollegeType collegeType = CollegeType.valueOf(collegeTypeId);
      traitPersister.restoreTrait(collegeElement, collegeModel.getCollege(collegeType));
    }
  }
}