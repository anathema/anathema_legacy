package net.sf.anathema.characterengine.persona;

import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.rules.Rule;

import static net.sf.anathema.characterengine.persona.Permission.Denied;

public class RuleBasedExecutor {
  private final Iterable<Rule> rules;

  public RuleBasedExecutor(Iterable<Rule> rules) {
    this.rules = rules;
  }

  public void executeIfPermitted(QualityClosure closure, Quality quality) {
    Quality copy = quality.copy();
    closure.execute(copy);
    for (Rule rule : rules) {
      if (rule.permits(copy) == Denied) {
        return;
      }
    }
    closure.execute(quality);
  }
}