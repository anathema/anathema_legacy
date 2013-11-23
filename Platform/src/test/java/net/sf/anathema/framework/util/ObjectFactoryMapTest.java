package net.sf.anathema.framework.util;

import net.sf.anathema.framework.environment.ObjectFactory;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ObjectFactoryMapTest {

  public static final String ADDITIONAL_PARAMETER = "AdditionalParameter";

  @Test
  public void usesAllGivenParameters() throws Exception {
    ObjectFactory factory = mock(ObjectFactory.class);
    new ReflectionFactoryMap<>(factory, DummyTypeFactory.class, ADDITIONAL_PARAMETER).get(DummyType.class);
    verify(factory).instantiateAllImplementers(DummyTypeFactory.class, ADDITIONAL_PARAMETER);
  }
}
