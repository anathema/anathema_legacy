package net.sf.anathema.character.sidereal.colleges.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.library.trait.FavorableTrait;
import net.sf.anathema.character.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.library.trait.IFavorableTrait;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.GrumpyIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.IFavorableStateChangedListener;
import net.sf.anathema.character.library.trait.rules.FavorableTraitRules;
import net.sf.anathema.character.sidereal.caste.ISiderealCasteVisitor;
import net.sf.anathema.character.sidereal.caste.SiderealCaste;
import net.sf.anathema.character.sidereal.colleges.presenter.IAstrologicalHouse;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.util.Identificate;

public class AstrologicalHouse extends Identificate implements IAstrologicalHouse {

  public static IAstrologicalHouse createAstrologicalHouse(SiderealCaste caste, ICharacterModelContext context) {
    final List<CollegeType> collegeTypes = new ArrayList<CollegeType>();
    caste.accept(new ISiderealCasteVisitor() {

      public void visitJourneys(SiderealCaste visitedCaste) {
        collegeTypes.add(CollegeType.Captain);
        collegeTypes.add(CollegeType.Gull);
        collegeTypes.add(CollegeType.Mast);
        collegeTypes.add(CollegeType.Messenger);
        collegeTypes.add(CollegeType.ShipsWheel);
      }

      public void visitSerenity(SiderealCaste visitedCaste) {
        collegeTypes.add(CollegeType.Ewer);
        collegeTypes.add(CollegeType.Lovers);
        collegeTypes.add(CollegeType.Musician);
        collegeTypes.add(CollegeType.Peacock);
        collegeTypes.add(CollegeType.Pillar);
      }

      public void visitBattles(SiderealCaste visitedCaste) {
        collegeTypes.add(CollegeType.Banner);
        collegeTypes.add(CollegeType.Gauntlet);
        collegeTypes.add(CollegeType.Quiver);
        collegeTypes.add(CollegeType.Shield);
        collegeTypes.add(CollegeType.Spear);
      }

      public void visitSecrets(SiderealCaste visitedCaste) {
        collegeTypes.add(CollegeType.Guardians);
        collegeTypes.add(CollegeType.Key);
        collegeTypes.add(CollegeType.Mask);
        collegeTypes.add(CollegeType.Sorcerer);
        collegeTypes.add(CollegeType.TreasureTrove);
      }

      public void visitEndings(SiderealCaste visitedCaste) {
        collegeTypes.add(CollegeType.Corpse);
        collegeTypes.add(CollegeType.Crow);
        collegeTypes.add(CollegeType.Haywain);
        collegeTypes.add(CollegeType.RisingSmoke);
        collegeTypes.add(CollegeType.Sword);
      }
    });
    CollegeType[] collegeyTypes = collegeTypes.toArray(new CollegeType[collegeTypes.size()]);
    return new AstrologicalHouse(caste.getId(), collegeyTypes, caste, context);
  }

  private IFavorableTrait[] colleges;
  private final ChangeControl collegeValueChangeControl = new ChangeControl();
  private final IIntValueChangedListener collegeValueChangeListener = new IIntValueChangedListener() {
    public void valueChanged(int newValue) {
      collegeValueChangeControl.fireChangedEvent();
    }
  };
  private final IFavorableStateChangedListener collegeStateChangeListener = new IFavorableStateChangedListener() {
    public void favorableStateChanged(FavorableState state) {
      collegeValueChangeControl.fireChangedEvent();
    }
  };

  private AstrologicalHouse(
      String id,
      CollegeType[] collegeTypes,
      ICasteType<ISiderealCasteVisitor> casteType,
      ICharacterModelContext context) {
    super(id);
    colleges = new IFavorableTrait[collegeTypes.length];
    for (int index = 0; index < collegeTypes.length; index++) {
      ITraitContext traitContext = context.getTraitContext();
      ITraitTemplate houseTemplate = SimpleTraitTemplate.createEssenceLimitedTemplate(0);
      FavorableTraitRules rules = new FavorableTraitRules(
          collegeTypes[index],
          houseTemplate,
          traitContext.getLimitationContext());
      colleges[index] = new FavorableTrait(
          rules,
          casteType,
          traitContext.getTraitValueStrategy(),
          context.getBasicCharacterContext(),
          context.getCharacterListening(),
          new FriendlyValueChangeChecker(),
          new GrumpyIncrementChecker());
      colleges[index].addCurrentValueListener(collegeValueChangeListener);
      colleges[index].getFavorization().addFavorableStateChangedListener(collegeStateChangeListener);
    }
  }

  public IFavorableTrait[] getColleges() {
    return colleges;
  }

  public void addChangeListener(IChangeListener listener) {
    collegeValueChangeControl.addChangeListener(listener);
  }
}