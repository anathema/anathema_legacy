package net.sf.anathema.character.sidereal.colleges.model;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.library.trait.IFavorableDefaultTrait;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.GrumpyIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.IFavorableStateChangedListener;
import net.sf.anathema.character.library.trait.rules.FavorableTraitRules;
import net.sf.anathema.character.sidereal.caste.SiderealCaste;
import net.sf.anathema.character.sidereal.colleges.presenter.IAstrologicalHouse;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.util.Identificate;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

public class AstrologicalHouse extends Identificate implements IAstrologicalHouse {

  private static final int collegesPerHouse = 5;

  public static IAstrologicalHouse createAstrologicalHouse(SiderealCaste caste, ICharacterModelContext context) {
    final List<CollegeType> collegeTypeList = new ArrayList<CollegeType>(collegesPerHouse);
    switch (caste) {
      case Journeys: {
        collegeTypeList.add(CollegeType.Captain);
        collegeTypeList.add(CollegeType.Gull);
        collegeTypeList.add(CollegeType.Mast);
        collegeTypeList.add(CollegeType.Messenger);
        collegeTypeList.add(CollegeType.ShipsWheel);
        break;
      }
      case Serenity: {
        collegeTypeList.add(CollegeType.Ewer);
        collegeTypeList.add(CollegeType.Lovers);
        collegeTypeList.add(CollegeType.Musician);
        collegeTypeList.add(CollegeType.Peacock);
        collegeTypeList.add(CollegeType.Pillar);
        break;
      }
      case Battles: {
        collegeTypeList.add(CollegeType.Banner);
        collegeTypeList.add(CollegeType.Gauntlet);
        collegeTypeList.add(CollegeType.Quiver);
        collegeTypeList.add(CollegeType.Shield);
        collegeTypeList.add(CollegeType.Spear);
        break;
      }
      case Secrets: {
        collegeTypeList.add(CollegeType.Guardians);
        collegeTypeList.add(CollegeType.Key);
        collegeTypeList.add(CollegeType.Mask);
        collegeTypeList.add(CollegeType.Sorcerer);
        collegeTypeList.add(CollegeType.TreasureTrove);
        break;
      }
      case Endings: {
        collegeTypeList.add(CollegeType.Corpse);
        collegeTypeList.add(CollegeType.Crow);
        collegeTypeList.add(CollegeType.Haywain);
        collegeTypeList.add(CollegeType.RisingSmoke);
        collegeTypeList.add(CollegeType.Sword);
        break;
      }
    }
    CollegeType[] collegeTypes = collegeTypeList.toArray(new CollegeType[collegesPerHouse]);
    return new AstrologicalHouse(caste.getId(), collegeTypes, caste, context);
  }

  private final IFavorableDefaultTrait[] colleges;
  private final Announcer<IChangeListener> collegeValueChangeControl = Announcer.to(IChangeListener.class);
  private final IIntValueChangedListener collegeValueChangeListener = new IIntValueChangedListener() {
    @Override
    public void valueChanged(int newValue) {
      collegeValueChangeControl.announce().changeOccurred();
    }
  };
  private final IFavorableStateChangedListener collegeStateChangeListener = new IFavorableStateChangedListener() {
    @Override
    public void favorableStateChanged(FavorableState state) {
      collegeValueChangeControl.announce().changeOccurred();
    }
  };

  private AstrologicalHouse(String id, CollegeType[] collegeTypes, ICasteType casteType, ICharacterModelContext context) {
    super(id);
    colleges = new IFavorableDefaultTrait[collegeTypes.length];
    for (int index = 0; index < collegeTypes.length; index++) {
      ITraitContext traitContext = context.getTraitContext();
      ITraitTemplate houseTemplate = SimpleTraitTemplate.createEssenceLimitedTemplate(0);
      FavorableTraitRules rules = new FavorableTraitRules(
          collegeTypes[index],
          houseTemplate,
          traitContext.getLimitationContext());
      colleges[index] = new DefaultTrait(
          rules,
          new ICasteType[] { casteType },
          traitContext,
          context.getBasicCharacterContext(),
          context.getCharacterListening(),
          new FriendlyValueChangeChecker(),
          new GrumpyIncrementChecker());
      colleges[index].addCurrentValueListener(collegeValueChangeListener);
      colleges[index].getFavorization().addFavorableStateChangedListener(collegeStateChangeListener);
    }
  }

  @Override
  public IFavorableDefaultTrait[] getColleges() {
    return colleges;
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    collegeValueChangeControl.addListener(listener);
  }
}