package net.sf.anathema.lib.compare;

import net.sf.anathema.lib.util.IIdentificate;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class I18nedIdentificateSorterTest {

  @Test
  public void worksOnEmptyArrays() throws Exception {
    I18nedIdentificateSorter sorter = new I18nedIdentificateSorter();
    IIdentificate[] identificates = sorter.sortAscending(new IIdentificate[0], new IIdentificate[0], null);
    assertThat(identificates.length, is(0));
  }
}
