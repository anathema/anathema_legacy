package net.sf.anathema.character.equipment.impl.creation.model;

import junit.framework.TestCase;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.IIntValueChangedListener;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WeaponDamageModelTest extends TestCase {

  private WeaponDamageModel damageModel;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    damageModel = new WeaponDamageModel();
  }

  public void testCreation() throws Exception {
    assertEquals(0, damageModel.getDamageModel().getValue());
    assertEquals(HealthType.Lethal, damageModel.getHealthType());
  }

  public void testEventOnHealthTypeChange() throws Exception {
    ChangeListener changeListener = mock(ChangeListener.class);
    damageModel.addHealthTypeChangeListener(changeListener);
    damageModel.setHealthType(HealthType.Aggravated);
    verify(changeListener).changeOccurred();
  }

  public void testEventOnIntValueChange() throws Exception {
    IIntValueChangedListener changeListener = mock(IIntValueChangedListener.class);
    damageModel.getDamageModel().addIntValueChangeListener(changeListener);
    damageModel.getDamageModel().setValue(2);
    verify(changeListener).valueChanged(2);
  }
}