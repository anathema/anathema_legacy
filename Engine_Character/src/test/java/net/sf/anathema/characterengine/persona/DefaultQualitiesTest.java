package net.sf.anathema.characterengine.persona;

import net.sf.anathema.characterengine.engine.Engine;
import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.Type;
import net.sf.anathema.characterengine.rules.AlwaysRule;
import net.sf.anathema.characterengine.rules.NeverRule;
import net.sf.anathema.characterengine.support.NumericQuality;
import net.sf.anathema.characterengine.support.NumericValue;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class DefaultQualitiesTest {
  private Engine engine = mock(Engine.class);
  private DefaultQualities qualities = new DefaultQualities(engine);
  private QualityClosure closure = mock(QualityClosure.class);
  private Type type = new Type("type");

  @Test
  public void createsQualitiesViaEngine() throws Exception {
    Name name = new Name("name");
    QualityKey key = new QualityKey(type, name);
    NumericQuality quality = new NumericQuality(new NumericValue(0));
    configureEngineToCreate(key, quality);
    qualities.addQuality(key);
    qualities.doFor(key, closure);
    verify(closure).execute(quality);
  }

  @Test
  public void ignoresUnknownQualities() throws Exception {
    Type type = new Type("unknownType");
    Name name = new Name("unknownName");
    qualities.doFor(new QualityKey(type, name), closure);
    verifyZeroInteractions(closure);
  }

  @Test
  public void executesClosureOnEachQualityOfGivenType() throws Exception {
    NumericQuality firstQuality = new NumericQuality(new NumericValue(0));
    NumericQuality secondQuality = new NumericQuality(new NumericValue(0));
    QualityKey firstName = new QualityKey(type, new Name("firstName"));
    QualityKey secondName = new QualityKey(type, new Name("secondName"));
    configureEngineToCreate(firstName, firstQuality);
    configureEngineToCreate(secondName, secondQuality);
    qualities.addQuality(firstName);
    qualities.addQuality(secondName);
    qualities.doForEach(type, closure);
    verify(closure).execute(firstQuality);
    verify(closure).execute(secondQuality);
  }

  @Test
  public void checksWithStandingRulesBeforeExecuting() throws Exception {
    NumericQuality firstQuality = new NumericQuality(new NumericValue(1));
    QualityKey quality = new QualityKey(type, new Name("firstName"));
    configureEngineToCreate(quality, firstQuality);
    qualities.addQuality(quality);
    forbidAnyInteractionWithQuality(type);
    qualities.doForEach(type, closure);
    verify(closure, never()).execute(firstQuality);
  }

  @Test
  public void executesDisregardingRulesIfExpresslyDemanded() throws Exception {
    NumericQuality firstQuality = new NumericQuality(new NumericValue(1));
    QualityKey quality = new QualityKey(type, new Name("firstName"));
    configureEngineToCreate(quality, firstQuality);
    qualities.addQuality(quality);
    forbidAnyInteractionWithQuality(type);
    qualities.doForEachDisregardingRules(type, closure);
    verify(closure).execute(firstQuality);
  }

  @Test
  public void executesCommandIfRulesAllow() throws Exception {
    NumericQuality firstQuality = new NumericQuality(new NumericValue(1));
    QualityKey quality = new QualityKey(type, new Name("firstName"));
    configureEngineToCreate(quality, firstQuality);
    qualities.addQuality(quality);
    allowAnyInteractionWithQuality(type);
    qualities.doForEach(type, closure);
    verify(closure).execute(firstQuality);
  }

  @Test
  public void forbiddingTrumpsPermission() throws Exception {
    NumericQuality firstQuality = new NumericQuality(new NumericValue(1));
    QualityKey quality = new QualityKey(type, new Name("firstName"));
    configureEngineToCreate(quality, firstQuality);
    qualities.addQuality(quality);
    forbidAnyInteractionWithQuality(type);
    allowAnyInteractionWithQuality(type);
    qualities.doForEach(type, closure);
    verify(closure, never()).execute(firstQuality);
  }

  @Test
  public void respectsAllRules() throws Exception {
    NumericQuality firstQuality = new NumericQuality(new NumericValue(1));
    QualityKey quality = new QualityKey(type, new Name("firstName"));
    configureEngineToCreate(quality, firstQuality);
    qualities.addQuality(quality);
    allowAnyInteractionWithQuality(type);
    forbidAnyInteractionWithQuality(type);
    qualities.doForEach(type, closure);
    verify(closure, never()).execute(firstQuality);
  }

  @Test
  public void respectsRulesWhenWorkingWithASingleQuality() throws Exception {
    NumericQuality firstQuality = new NumericQuality(new NumericValue(1));
    QualityKey quality = new QualityKey(type, new Name("firstName"));
    configureEngineToCreate(quality, firstQuality);
    qualities.addQuality(quality);
    forbidAnyInteractionWithQuality(type);
    qualities.doFor(quality, closure);
    verify(closure, never()).execute(firstQuality);
  }

  @Test
  public void doesNotApplyRulesForADifferentType() throws Exception {
    Type differentType = new Type("differentType");
    NumericQuality firstQuality = new NumericQuality(new NumericValue(1));
    QualityKey quality = new QualityKey(type, new Name("firstName"));
    configureEngineToCreate(quality, firstQuality);
    qualities.addQuality(quality);
    forbidAnyInteractionWithQuality(differentType);
    qualities.doForEach(type, closure);
    verify(closure).execute(firstQuality);
  }

  @Test
  public void doesNotExecuteClosureOnQualityOfDifferentType() throws Exception {
    Type differentType = new Type("differentType");
    NumericQuality firstQuality = new NumericQuality(new NumericValue(0));
    QualityKey firstName = new QualityKey(differentType, new Name("firstName"));
    configureEngineToCreate(firstName, firstQuality);
    qualities.addQuality(firstName);
    qualities.doForEach(type, closure);
    verifyZeroInteractions(closure);
  }

  private void forbidAnyInteractionWithQuality(Type type) {
    qualities.defineRule(type, new NeverRule());
  }

  private void allowAnyInteractionWithQuality(Type type) {
    qualities.defineRule(type, new AlwaysRule());
  }

  private void configureEngineToCreate(QualityKey key, NumericQuality quality) {
    when(engine.createQuality(key)).thenReturn(quality);
  }
}
