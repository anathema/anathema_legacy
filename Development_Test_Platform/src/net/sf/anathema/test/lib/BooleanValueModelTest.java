package net.sf.anathema.test.lib;

import org.easymock.EasyMock;

import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;

public class BooleanValueModelTest extends BasicTestCase {

  public void testCreation() throws Exception {
    BooleanValueModel model = new BooleanValueModel(false);
    assertFalse(model.getValue());
  }

  public void testEventFired() throws Exception {
    BooleanValueModel model = new BooleanValueModel(false);
    IBooleanValueChangedListener listener = EasyMock.createMock(IBooleanValueChangedListener.class);
    model.addChangeListener(listener);
    Object[] mocks = new Object[] { listener };
    listener.valueChanged(true);
    EasyMock.replay(mocks);
    model.setValue(true);
    EasyMock.verify(mocks);
  }
}