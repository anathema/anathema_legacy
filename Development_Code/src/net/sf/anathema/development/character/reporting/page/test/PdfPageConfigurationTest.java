package net.sf.anathema.development.character.reporting.page.test;

import net.sf.anathema.development.character.reporting.page.PdfPageConfiguration;
import net.sf.anathema.lib.testing.BasicTestCase;

public class PdfPageConfigurationTest extends BasicTestCase {

  public void testContentWidthEquals3ColumnWidth() throws Exception {
    PdfPageConfiguration pageConfiguration = new PdfPageConfiguration();
    assertEquals(pageConfiguration.getContentWidth(), pageConfiguration.getColumnWidth(3));
  }
}