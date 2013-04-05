package net.sf.anathema.lib.compare;

import net.sf.anathema.lib.util.Identified;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class I18nedIdentificateSorterTest {

  @Test
  public void worksOnEmptyArrays() throws Exception {
    I18nedIdentificateSorter sorter = new I18nedIdentificateSorter();
    Identified[] identificates = sorter.sortAscending(new Identified[0], new Identified[0], null);
    assertThat(identificates.length, is(0));
  }
}
