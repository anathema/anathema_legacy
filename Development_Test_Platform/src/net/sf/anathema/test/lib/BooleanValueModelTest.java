package net.sf.anathema.test.lib;

import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

public class BooleanValueModelTest {

  @Test
  public void testCreation() throws Exception {
    BooleanValueModel model = new BooleanValueModel(false);
    Assert.assertFalse(model.getValue());
  }

  @Test
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