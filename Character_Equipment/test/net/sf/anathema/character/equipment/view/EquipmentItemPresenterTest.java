package net.sf.anathema.character.equipment.view;

import junit.framework.TestCase;
import net.sf.anathema.character.equipment.character.EquipmentHeroEvaluator;
import net.sf.anathema.character.equipment.character.EquipmentOptionsProvider;
import net.sf.anathema.character.equipment.character.IEquipmentStringBuilder;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.dummy.DemoMeleeWeapon;
import net.sf.anathema.character.equipment.dummy.DummyEquipmentObject;
import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.main.library.trait.specialties.Specialty;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.framework.resources.LocaleResources;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentObjectPresenter;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentObjectView;
import net.sf.anathema.hero.equipment.display.presenter.StatsView;
import net.sf.anathema.hero.equipment.display.view.CheckBoxStatsView;
import net.sf.anathema.hero.health.HealthType;
import net.sf.anathema.lib.util.SimpleIdentifier;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EquipmentItemPresenterTest extends TestCase {

  private IEquipmentStringBuilder equipmentStringBuilder = new IEquipmentStringBuilder() {
    @Override
    public String createString(IEquipmentItem item, IEquipmentStats equipment) {
      if (equipment.getName().getId().equals("Sword")) {
        return "Passt!";
      }
      throw new IllegalArgumentException();
    }
  };

  public void testNameOnlyEquipment() throws Exception {
    IEquipmentItem model = new DummyEquipmentObject("First and Forsaken Weapon", null);
    EquipmentObjectView view = mock(EquipmentObjectView.class);
    initPresentation(model, view);
    verify(view).setItemTitle("First and Forsaken Weapon");
  }

  private void initPresentation(IEquipmentItem model, EquipmentObjectView view) {
    EquipmentHeroEvaluator dataProvider = mock(EquipmentHeroEvaluator.class);
    EquipmentOptionsProvider optionProvider = mock(EquipmentOptionsProvider.class);
    when(dataProvider.getSpecialties(isA(TraitType.class))).thenReturn(new Specialty[0]);
    new EquipmentObjectPresenter(model, view, equipmentStringBuilder, dataProvider, optionProvider, new LocaleResources()).initPresentation();
  }

  public void testEquipmentWithoutStats() throws Exception {
    EquipmentObjectView view = mock(EquipmentObjectView.class);
    DummyEquipmentObject model = new DummyEquipmentObject("First and Forsaken Weapon", "Abyssal-Weapon mit Bums");
    initPresentation(model, view);
    verify(view).setItemTitle("First and Forsaken Weapon");
    verify(view).setItemDescription("Abyssal-Weapon mit Bums");
  }

  public void testEquipmentWithCloseCombatStats() throws Exception {
    EquipmentObjectView view = mock(EquipmentObjectView.class);
    view.setItemTitle("Title");
    StatsView isPrintSelectedModel = new CheckBoxStatsView("");
    when(view.addStats("Passt!")).thenReturn(isPrintSelectedModel);
    DummyEquipmentObject model = new DummyEquipmentObject("Title", null);
    model.addEquipment(new DemoMeleeWeapon(new SimpleIdentifier("Sword"), 5, 2, 7, 1, HealthType.Lethal, -1, 0, 2));
    initPresentation(model, view);
  }

  public void testPrintModelInitialization() throws Exception {
    EquipmentObjectView view = mock(EquipmentObjectView.class);
    view.setItemTitle("Title");
    StatsView isPrintSelectedModel = new CheckBoxStatsView("");
    when(view.addStats("Passt!")).thenReturn(isPrintSelectedModel);
    DummyEquipmentObject model = new DummyEquipmentObject("Title", null);
    model.addEquipment(new DemoMeleeWeapon(new SimpleIdentifier("Sword"), 5, 2, 7, 1, HealthType.Lethal, -1, 0, 2));
    initPresentation(model, view);
    assertFalse(isPrintSelectedModel.getSelected());
  }
}