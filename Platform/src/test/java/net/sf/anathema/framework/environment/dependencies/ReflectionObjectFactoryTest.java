package net.sf.anathema.framework.environment.dependencies;

import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReflectionObjectFactoryTest {

  private AnnotationFinder finder = mock(AnnotationFinder.class);
  private ReflectionObjectFactory factory = new ReflectionObjectFactory(finder);
  private Set<Class<?>> annotatedClasses = new LinkedHashSet<>();

  @Before
  public void configureAnnotationFinder() throws Exception {
    when(finder.getTypesAnnotatedWith(DummyAnnotation.class)).thenReturn(annotatedClasses);
  }

  @Test
  public void findsNoClassesWhenAnnotationUnknown() {
    Collection<Object> result = factory.instantiateAll(DummyAnnotation.class);
    assertThat(result, hasSize(0));
  }

  @Test
  public void instantiatesClassWhenFinderDiscoversThem() {
    annotatedClasses.add(AnnotatedDummy.class);
    Collection<Object> result = factory.instantiateAll(DummyAnnotation.class);
    assertThat(result, contains(is(instanceOf(AnnotatedDummy.class))));
  }

  @Test
  public void usesConstructorArguments() {
    annotatedClasses.add(AnnotatedDummy.class);
    Collection<AnnotatedDummy> result = factory.instantiateAll(DummyAnnotation.class, "Parameter");
    Iterables.getOnlyElement(result).assertStringConstructorWasUsed();
  }

  @Test
  public void instantiatesAllClassesAnnotated() {
    annotatedClasses.add(AnnotatedDummy.class);
    annotatedClasses.add(AnotherAnnotatedDummy.class);
    Collection<AnnotatedDummy> result = factory.instantiateAll(DummyAnnotation.class);
    assertThat(result, hasSize(2));
  }

  @Test
  public void ordersInstancesByWeight() {
    annotatedClasses.add(AnnotatedDummy.class);
    annotatedClasses.add(AnotherAnnotatedDummy.class);
    Collection<AnnotatedDummy> result = factory.instantiateOrdered(DummyAnnotation.class);
    assertThat(Iterables.get(result, 0), is(instanceOf(AnotherAnnotatedDummy.class)));
    assertThat(Iterables.get(result, 1), is(instanceOf(AnnotatedDummy.class)));
  }
}