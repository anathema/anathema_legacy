package net.sf.anathema.character.equipment.view;

import junit.framework.TestCase;
import net.sf.anathema.character.equipment.character.EquipmentHeroEvaluator;
import net.sf.anathema.character.equipment.character.EquipmentObjectPresenter;
import net.sf.anathema.character.equipment.character.EquipmentOptionsProvider;
import net.sf.anathema.character.equipment.character.IEquipmentStringBuilder;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.view.IEquipmentObjectView;
import net.sf.anathema.character.equipment.dummy.DemoMeleeWeapon;
import net.sf.anathema.character.equipment.dummy.DummyEquipmentObject;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.specialties.Specialty;
import net.sf.anathema.framework.resources.LocaleResources;
import net.sf.anathema.lib.model.BooleanModel;
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
    IEquipmentObjectView view = mock(IEquipmentObjectView.class);
    initPresentation(model, view);
    verify(view).setItemTitle("First and Forsaken Weapon");
  }

  private void initPresentation(IEquipmentItem model, IEquipmentObjectView view) {
    EquipmentHeroEvaluator dataProvider = mock(EquipmentHeroEvaluator.class);
    EquipmentOptionsProvider optionProvider = mock(EquipmentOptionsProvider.class);
    when(dataProvider.getSpecialties(isA(TraitType.class))).thenReturn(new Specialty[0]);
    new EquipmentObjectPresenter(model, view, equipmentStringBuilder, dataProvider, optionProvider, new LocaleResources()).initPresentation();
  }

  public void testEquipmentWithoutStats() throws Exception {
    IEquipmentObjectView view = mock(IEquipmentObjectView.class);
    DummyEquipmentObject model = new DummyEquipmentObject("First and Forsaken Weapon", "Abyssal-Weapon mit Bums");
    initPresentation(model, view);
    verify(view).setItemTitle("First and Forsaken Weapon");
    verify(view).setItemDescription("Abyssal-Weapon mit Bums");
  }

  public void testEquipmentWithCloseCombatStats() throws Exception {
    IEquipmentObjectView view = mock(IEquipmentObjectView.class);
    view.setItemTitle("Title");
    BooleanModel isPrintSelectedModel = new BooleanModel();
    when(view.addStats("Passt!")).thenReturn(isPrintSelectedModel);
    DummyEquipmentObject model = new DummyEquipmentObject("Title", null);
    model.addEquipment(new DemoMeleeWeapon(new SimpleIdentifier("Sword"), 5, 2, 7, 1, HealthType.Lethal, -1, 0, 2));
    initPresentation(model, view);
  }

  public void testPrintModelInitialization() throws Exception {
    IEquipmentObjectView view = mock(IEquipmentObjectView.class);
    view.setItemTitle("Title");
    BooleanModel isPrintSelectedModel = new BooleanModel();
    when(view.addStats("Passt!")).thenReturn(isPrintSelectedModel);
    DummyEquipmentObject model = new DummyEquipmentObject("Title", null);
    model.addEquipment(new DemoMeleeWeapon(new SimpleIdentifier("Sword"), 5, 2, 7, 1, HealthType.Lethal, -1, 0, 2));
    initPresentation(model, view);
    assertFalse(isPrintSelectedModel.getValue());
  }
}