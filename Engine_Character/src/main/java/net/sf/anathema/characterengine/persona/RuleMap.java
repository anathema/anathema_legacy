package net.sf.anathema.characterengine.persona;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.Type;
import net.sf.anathema.characterengine.quality.TypeClosure;
import net.sf.anathema.characterengine.rules.AlwaysRule;
import net.sf.anathema.characterengine.rules.Rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RuleMap {
  private Multimap<Type, Rule> rules = ArrayListMultimap.create();

  public void put(Type type, Rule rule) {
    rules.put(type, rule);
  }

  public Collection<Rule> getAllForType(Type type) {
    if (rules.get(type).isEmpty()) {
      return unconditionalPermission();
    }
    return rules.get(type);
  }

  private List<Rule> unconditionalPermission() {
    return Collections.singletonList((Rule) new AlwaysRule());
  }

  public Iterable<Rule> getAllForQuality(QualityKey qualityKey) {
    final List<Rule> applicableRules = new ArrayList<>();
    qualityKey.withTypeDo(new TypeClosure() {
      @Override
      public void execute(Type type) {
        applicableRules.addAll(getAllForType(type));
      }
    });
    return applicableRules;
  }
}