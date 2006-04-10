package net.sf.anathema.character.sidereal.colleges.model;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.trait.IFavorableTrait;
import net.sf.anathema.character.sidereal.caste.SiderealCaste;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeTemplate;
import net.sf.anathema.character.sidereal.colleges.presenter.IAstrologicalHouse;
import net.sf.anathema.character.sidereal.colleges.presenter.ISiderealCollegeModel;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class SiderealCollegeModel implements ISiderealCollegeModel {

  private final ICharacterModelContext context;

  private static IAstrologicalHouse[] createAstrologicalHouses(ICharacterModelContext context) {
    SiderealCaste[] siderealCastes = SiderealCaste.values();
    IAstrologicalHouse[] houses = new IAstrologicalHouse[siderealCastes.length];
    for (int index = 0; index < siderealCastes.length; index++) {
      houses[index] = AstrologicalHouse.createAstrologicalHouse(siderealCastes[index], context);
    }
    return houses;
  }

  private final IAstrologicalHouse[] allHouses;
  private final SiderealCollegeTemplate template;
  private final ChangeControl bonusControl = new ChangeControl();

  private final IChangeListener houseChangeListener = new IChangeListener() {
    public void changeOccured() {
      bonusControl.fireChangedEvent();
    }
  };

  public SiderealCollegeModel(SiderealCollegeTemplate template, ICharacterModelContext context) {
    this.template = template;
    this.context = context;
    allHouses = createAstrologicalHouses(context);
    for (IAstrologicalHouse house : allHouses) {
      house.addChangeListener(houseChangeListener);
    }
  }

  public IAstrologicalHouse[] getAllHouses() {
    return allHouses;
  }

  public String getTemplateId() {
    return template.getId();
  }

  public IFavorableTrait getCollege(CollegeType type) {
    for (IAstrologicalHouse house : getAllHouses()) {
      for (IFavorableTrait college : house.getColleges()) {
        if (college.getType() == type) {
          return college;
        }
      }
    }
    throw new UnreachableCodeReachedException();
  }

  public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    return new CollegeModelBonusPointCalculator(allHouses, template.getBonusCosts(), template.getPoints());
  }

  public void addBonusPointsChangeListener(IChangeListener listener) {
    bonusControl.addChangeListener(listener);
  }

  public IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return new CollegeModelExperienceCalculator(allHouses, template.getExperienceCosts());
  }

  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Miscellaneous;
  }

  public int getTotalFavoredDotCount() {
    return template.getPoints().getFavoredDotCount();
  }

  public int getTotalGeneralDotCount() {
    return template.getPoints().getDefaultDotCount();
  }

  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    context.getCharacterListening().addChangeListener(listener);
  }
}