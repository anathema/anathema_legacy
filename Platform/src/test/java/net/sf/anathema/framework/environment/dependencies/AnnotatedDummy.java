package net.sf.anathema.framework.environment.dependencies;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@Weight(weight = 2)
public class AnnotatedDummy implements DummyInterface{

  private String argument;

  public AnnotatedDummy() {
  }

  public AnnotatedDummy(String argument) {
    this.argument = argument;
  }

  public void assertStringConstructorWasUsed() {
    assertThat(argument, is(not(nullValue())));
  }
}
