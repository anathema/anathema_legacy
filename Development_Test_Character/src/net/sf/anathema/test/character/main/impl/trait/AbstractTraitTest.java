package net.sf.anathema.test.character.main.impl.trait;

import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.test.character.BasicCharacterTestCase;

import org.easymock.MockControl;

public abstract class AbstractTraitTest extends BasicCharacterTestCase {

  private DefaultTrait objectUnderTest;
  private MockControl intListenerControl;
  private IIntValueChangedListener intListener;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    objectUnderTest = createObjectUnderTest();
    this.intListenerControl = MockControl.createStrictControl(IIntValueChangedListener.class);
    this.intListener = (IIntValueChangedListener) intListenerControl.getMock();
    objectUnderTest.addCreationPointListener(intListener);
  }

  public DefaultTrait getObjectUnderTest() {
    return objectUnderTest;
  }

  protected abstract DefaultTrait createObjectUnderTest();

  public void testExceedCreationValueMaximum() throws Exception {
    intListener.valueChanged(5);
    intListenerControl.replay();
    objectUnderTest.setCurrentValue(6);
    assertEquals(5, objectUnderTest.getCreationValue());
    intListenerControl.verify();
  }

  public void testUnderrunCreationValueMinimum() throws Exception {
    intListener.valueChanged(objectUnderTest.getAbsoluteMinValue());
    intListenerControl.replay();
    objectUnderTest.setCurrentValue(-1);
    assertEquals(objectUnderTest.getAbsoluteMinValue(), objectUnderTest.getCreationValue());
    intListenerControl.verify();
  }

  public void testSetValueInRange() throws Exception {
    intListener.valueChanged(3);
    intListenerControl.replay();
    objectUnderTest.setCurrentValue(3);
    assertEquals(3, objectUnderTest.getCreationValue());
    intListenerControl.verify();
  }
}