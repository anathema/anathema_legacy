package net.sf.anathema.charmentry.model.test;

import net.sf.anathema.charmentry.model.data.ConfigurableMagicSource;
import net.sf.anathema.lib.testing.BasicTestCase;

public class ConfigurableMagicSourceTest extends BasicTestCase {

  private ConfigurableMagicSource source;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.source = new ConfigurableMagicSource();
  }

  public void testSetSource() throws Exception {
    String string = "Expected"; //$NON-NLS-1$
    source.setSource(string);
    assertEquals(string, source.getSource());
  }

  public void testSetPage() throws Exception {
    Integer page = 123;
    source.setPage(page);
    assertEquals(String.valueOf(page), source.getPage());
  }

  public void testSetNullSource() throws Exception {
    String string = null;
    source.setSource(string);
    assertEquals("Custom", source.getSource()); //$NON-NLS-1$
    assertNull(source.getPage());
  }

  public void testSetCustomSource() throws Exception {
    String string = "custOm"; //$NON-NLS-1$
    source.setSource(string);
    assertEquals("Custom", source.getSource()); //$NON-NLS-1$
    assertNull(source.getPage());
  }

  public void testSetPageForCustomSource() throws Exception {
    source.setSource("Custom"); //$NON-NLS-1$
    source.setPage(1);
    assertNull(source.getPage());
  }
}