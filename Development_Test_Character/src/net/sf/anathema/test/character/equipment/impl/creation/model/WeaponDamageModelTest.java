package net.sf.anathema.test.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.impl.creation.model.WeaponDamageModel;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.testing.BasicTestCase;

import org.easymock.EasyMock;

public class WeaponDamageModelTest extends BasicTestCase {

  private WeaponDamageModel damageModel;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    damageModel = new WeaponDamageModel();
  }

  public void testCreation() throws Exception {
    assertEquals(0, damageModel.getValue());
    assertEquals(HealthType.Lethal, damageModel.getHealthType());
  }

  public void testEventOnHealthTypeChange() throws Exception {
    IChangeListener changeListener = EasyMock.createMock(IChangeListener.class);
    damageModel.addHealthTypeChangeListener(changeListener);
    Object[] mocks = new Object[] { changeListener };
    changeListener.changeOccured();
    EasyMock.replay(mocks);
    damageModel.setHealthType(HealthType.Aggravated);
    EasyMock.verify(mocks);
  }

  public void testEventOnIntValueChange() throws Exception {
    IIntValueChangedListener changeListener = EasyMock.createMock(IIntValueChangedListener.class);
    damageModel.addIntValueChangeListener(changeListener);
    Object[] mocks = new Object[] { changeListener };
    changeListener.valueChanged(2);
    EasyMock.replay(mocks);
    damageModel.setValue(2);
    EasyMock.verify(mocks);
  }
}