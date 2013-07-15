package net.sf.anathema.lib.compare;

import net.sf.anathema.lib.util.Identifier;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class I18nedIdentificateSorterTest {

  @Test
  public void worksOnEmptyLists() throws Exception {
    I18nedIdentificateSorter sorter = new I18nedIdentificateSorter();
    List<Identifier> originalGroup = Collections.emptyList();
    List<Identifier> identificates = sorter.sortAscending(originalGroup, null);
    assertThat(identificates.size(), is(0));
  }
}
