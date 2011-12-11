package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.testing.BasicTestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
    IChangeListener changeListener = mock(IChangeListener.class);
    damageModel.addHealthTypeChangeListener(changeListener);
    damageModel.setHealthType(HealthType.Aggravated);
    verify(changeListener).changeOccured();
  }

  public void testEventOnIntValueChange() throws Exception {
    IIntValueChangedListener changeListener = mock(IIntValueChangedListener.class);
    damageModel.addIntValueChangeListener(changeListener);
    damageModel.setValue(2);
    verify(changeListener).valueChanged(2);
  }
}