package net.sf.anathema.test.character.main.impl.trait;

import net.sf.anathema.character.library.trait.AbstractFavorableTrait;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.test.character.BasicCharacterTestCase;

import org.easymock.EasyMock;

public abstract class AbstractTraitTest extends BasicCharacterTestCase {

  private DefaultTrait objectUnderTest;
  private IIntValueChangedListener intListener;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    objectUnderTest = createObjectUnderTest();
    this.intListener = EasyMock.createStrictMock(IIntValueChangedListener.class);
    objectUnderTest.addCreationPointListener(intListener);
  }

  public AbstractFavorableTrait getObjectUnderTest() {
    return objectUnderTest;
  }

  protected abstract DefaultTrait createObjectUnderTest();

  public void testExceedCreationValueMaximum() throws Exception {
    intListener.valueChanged(5);
    EasyMock.replay(intListener);
    objectUnderTest.setCurrentValue(6);
    assertEquals(5, objectUnderTest.getCreationValue());
    EasyMock.verify(intListener);
  }

  public void testUnderrunCreationValueMinimum() throws Exception {
    intListener.valueChanged(objectUnderTest.getAbsoluteMinValue());
    EasyMock.replay(intListener);
    objectUnderTest.setCurrentValue(-1);
    assertEquals(objectUnderTest.getAbsoluteMinValue(), objectUnderTest.getCreationValue());
    EasyMock.verify(intListener);
  }

  public void testSetValueInRange() throws Exception {
    intListener.valueChanged(3);
    EasyMock.replay(intListener);
    objectUnderTest.setCurrentValue(3);
    assertEquals(3, objectUnderTest.getCreationValue());
    EasyMock.verify(intListener);
  }
}