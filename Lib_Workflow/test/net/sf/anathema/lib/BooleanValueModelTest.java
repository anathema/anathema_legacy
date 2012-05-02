package net.sf.anathema.lib;

import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BooleanValueModelTest {
  private BooleanValueModel model = new BooleanValueModel(false);

  @Test
  public void storesFalseAfterCreation() throws Exception {
    Assert.assertFalse(model.getValue());
  }

  @Test
  public void notifiesListenersOfChange() throws Exception {
    IBooleanValueChangedListener listener = mock(IBooleanValueChangedListener.class);
    model.addChangeListener(listener);
    model.setValue(true);
    verify(listener).valueChanged(true);
  }
}