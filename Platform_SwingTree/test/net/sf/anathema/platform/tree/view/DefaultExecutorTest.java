package net.sf.anathema.platform.tree.view;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class DefaultExecutorTest {

  @Test
  public void doesNotActOnPreferredAction() throws Exception {
    Closure closure = mock(Closure.class);
    new DefaultExecutor().perform(closure);
    verifyZeroInteractions(closure);
  }

  @Test
  public void performsDefaultAction() throws Exception {
    Runnable action = mock(Runnable.class);
    new DefaultExecutor().orFallBackTo(action);
    verify(action).run();
  }

  @Test
  public void canChainCalls() throws Exception {
    Closure closure = mock(Closure.class);
    Runnable action = mock(Runnable.class);
    new DefaultExecutor().perform(closure).orFallBackTo(action);
    verify(action).run();
  }
}
