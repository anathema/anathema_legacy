package net.sf.anathema.test.platform.environment;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

public class DateParsingTest {

  @SuppressWarnings("deprecation")
  @Test
  public void testParseDate() throws ParseException {
    String dateString = "11/26/05"; //$NON-NLS-1$
    Date date = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ENGLISH).parse(dateString);
    Assert.assertEquals(26, date.getDate());
    Assert.assertEquals(10, date.getMonth());
    Assert.assertEquals(05, date.getYear());
  }
}