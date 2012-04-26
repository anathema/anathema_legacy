package net.sf.anathema.character.sidereal.colleges.model;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.trait.IFavorableDefaultTrait;
import net.sf.anathema.character.sidereal.caste.SiderealCaste;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeTemplate;
import net.sf.anathema.character.sidereal.colleges.presenter.IAstrologicalHouse;
import net.sf.anathema.character.sidereal.colleges.presenter.ISiderealCollegeModel;
import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

public class SiderealCollegeModel implements ISiderealCollegeModel {

  private final ICharacterModelContext context;

  private static IAstrologicalHouse[] createAstrologicalHouses(ICharacterModelContext context) {
    SiderealCaste[] siderealCastes = SiderealCaste.values();
    IAstrologicalHouse[] houses = new IAstrologicalHouse[siderealCastes.length];
    for (SiderealCaste caste : siderealCastes) {
      houses[caste.ordinal()] = AstrologicalHouse.createAstrologicalHouse(caste, context);
    }
    return houses;
  }

  private final IAstrologicalHouse[] allHouses;
  private final SiderealCollegeTemplate template;
  private final Announcer<IChangeListener> bonusControl = Announcer.to(IChangeListener.class);
  private final IChangeListener houseChangeListener = new IChangeListener() {
    @Override
    public void changeOccurred() {
      bonusControl.announce().changeOccurred();
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

  @Override
  public IAstrologicalHouse[] getAllHouses() {
    return allHouses;
  }

  @Override
  public String getTemplateId() {
    return template.getId();
  }

  public IFavorableDefaultTrait getCollege(CollegeType type) {
    for (IAstrologicalHouse house : getAllHouses()) {
      for (IFavorableDefaultTrait college : house.getColleges()) {
        if (college.getType() == type) {
          return college;
        }
      }
    }
    throw new UnreachableCodeReachedException();
  }

  @Override
  public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    return new CollegeModelBonusPointCalculator(allHouses, template.getBonusCosts(), template.getPoints());
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    bonusControl.addListener(listener);
  }

  @Override
  public IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return new CollegeModelExperienceCalculator(allHouses, template.getExperienceCosts());
  }

  @Override
  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Miscellaneous;
  }

  @Override
  public int getTotalFavoredDotCount() {
    return template.getPoints().getFavoredDotCount();
  }

  @Override
  public int getTotalGeneralDotCount() {
    return template.getPoints().getDefaultDotCount();
  }

  @Override
  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    context.getCharacterListening().addChangeListener(listener);
  }
}