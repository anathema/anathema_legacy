package net.sf.anathema.hero.concept.sheet.personal.content;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PersonalInfoRow implements Iterable<TitledInfo> {

  private List<TitledInfo> cells = new ArrayList<>();

  public PersonalInfoRow(TitledInfo... cells) {
    this.cells.addAll(Arrays.asList(cells));
  }

  public int getCellIndex(TitledInfo info) {
    int index = 0;
    for(TitledInfo cell : cells) {
      if (cell == info) {
        break;
      }
      index += cell.columnCount;
    }
    return index;
  }

  @Override
  public Iterator<TitledInfo> iterator() {
    return cells.iterator();
  }
}
