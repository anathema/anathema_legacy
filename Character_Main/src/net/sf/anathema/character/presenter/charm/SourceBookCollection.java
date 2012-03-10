package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class SourceBookCollection {

  public List<IExaltedSourceBook> getSourcesForEdition(IExaltedEdition edition) {
    List<IExaltedSourceBook> books = newArrayList();
    for (IExaltedSourceBook book : getAllKnownBooks()) {
      if (book.getEdition() == edition) {
        books.add(book);
      }
    }
    return books;
  }

  private ExaltedSourceBook[] getAllKnownBooks() {
    return ExaltedSourceBook.values();
  }
}
