package net.sf.anathema.characterengine.persona;

import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.rules.Rule;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.Collections;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RuleBasedExecutorTest {
  Rule rule = mock(Rule.class);
  Quality quality = mock(Quality.class);
  QualityClosure closure = mock(QualityClosure.class);

  @Test
  public void checksRulesWithAModifiedCopy() throws Exception {
    Quality copy = mock(Quality.class);
    when(quality.copy()).thenReturn(copy);
    Iterable<Rule> rules = Collections.singletonList(rule);
    new RuleBasedExecutor(rules).executeIfPermitted(closure, quality);
    InOrder inOrder = inOrder(closure, rule);
    inOrder.verify(closure).execute(copy);
    inOrder.verify(rule).permits(copy);
  }
}