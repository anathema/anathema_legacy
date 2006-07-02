package net.sf.anathema.test.character.equipment.character;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.character.equipment.character.EquipmentObjectPresenter;
import net.sf.anathema.character.equipment.character.EquipmentStringBuilder;
import net.sf.anathema.character.equipment.character.IEquipmentStringBuilder;
import net.sf.anathema.character.equipment.character.model.IEquipmentObject;
import net.sf.anathema.character.equipment.character.view.IEquipmentObjectView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipment;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.dummy.character.equipment.DemoMeleeWeapon;
import net.sf.anathema.dummy.character.equipment.DummyEquipmentObject;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.util.Identificate;
import net.sf.anathema.test.character.generic.framework.magic.stringbuilder.DummyResources;

import org.easymock.EasyMock;

public class EquipmentObjectPresenterTest extends BasicTestCase {

  private IResources resources = new DummyResources();
  private IEquipmentStringBuilder equipmentStringBuilder = new IEquipmentStringBuilder() {
    public String createString(IEquipment equipment) {
      if (equipment.getName().getId().equals("Sword")) { //$NON-NLS-1$
        return "Passt!"; //$NON-NLS-1$
      }
      throw new IllegalArgumentException();
    }
  };

  public void testNameOnlyEquipment() throws Exception {
    IEquipmentObject model = new DummyEquipmentObject("First and Forsaken Weapon", null); //$NON-NLS-1$
    IEquipmentObjectView view = EasyMock.createMock(IEquipmentObjectView.class);
    view.setItemTitle("First and Forsaken Weapon"); //$NON-NLS-1$
    EasyMock.replay(view);
    new EquipmentObjectPresenter(resources, model, view, equipmentStringBuilder).initPresentation();
    EasyMock.verify(view);
  }

  public void testEquipmentWithoutStats() throws Exception {
    IEquipmentObjectView view = EasyMock.createMock(IEquipmentObjectView.class);
    view.setItemTitle("First and Forsaken Weapon"); //$NON-NLS-1$
    view.setItemDescription("Abyssal-Weapon mit Bums"); //$NON-NLS-1$
    EasyMock.replay(view);
    DummyEquipmentObject model = new DummyEquipmentObject("First and Forsaken Weapon", //$NON-NLS-1$
        "Abyssal-Weapon mit Bums"); //$NON-NLS-1$
    new EquipmentObjectPresenter(resources, model, view, equipmentStringBuilder).initPresentation();
    EasyMock.verify(view);
  }

  public void testEquipmentWithCloseCombatStats() throws Exception {
    IEquipmentObjectView view = EasyMock.createMock(IEquipmentObjectView.class);
    view.setItemTitle("Title"); //$NON-NLS-1$
    BooleanModel isPrintSelectedModel = new BooleanModel();
    EasyMock.expect(view.addStats("Passt!")).andReturn(isPrintSelectedModel); //$NON-NLS-1$
    EasyMock.replay(view);
    DummyEquipmentObject model = new DummyEquipmentObject("Title", null); //$NON-NLS-1$
    model.addEquipment(new DemoMeleeWeapon(new Identificate("Sword"), 5, 2, 7, HealthType.Lethal, -1, 2)); //$NON-NLS-1$
    new EquipmentObjectPresenter(resources, model, view, equipmentStringBuilder).initPresentation();
    EasyMock.verify(view);
  }
  
  public void testPrintModelInitialization() throws Exception {
    IEquipmentObjectView view = EasyMock.createMock(IEquipmentObjectView.class);
    view.setItemTitle("Title"); //$NON-NLS-1$
    BooleanModel isPrintSelectedModel = new BooleanModel();
    EasyMock.expect(view.addStats("Passt!")).andReturn(isPrintSelectedModel); //$NON-NLS-1$
    EasyMock.replay(view);
    DummyEquipmentObject model = new DummyEquipmentObject("Title", null); //$NON-NLS-1$
    model.addEquipment(new DemoMeleeWeapon(new Identificate("Sword"), 5, 2, 7, HealthType.Lethal, -1, 2)); //$NON-NLS-1$
    new EquipmentObjectPresenter(resources, model, view, equipmentStringBuilder).initPresentation();
    assertFalse(isPrintSelectedModel.getValue());
  }
}