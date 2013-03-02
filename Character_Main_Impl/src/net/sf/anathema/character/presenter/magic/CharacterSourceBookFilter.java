package net.sf.anathema.character.presenter.magic;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.impl.rules.SourceBook;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import org.dom4j.Element;

import java.util.List;

import static net.sf.anathema.character.impl.persistence.SecondEdition.SECOND_EDITION;

public class CharacterSourceBookFilter extends SourceBookCharmFilter {

  private static final String TAG_FILTERNAME = "SourceFilter";
  private static final String TAG_SOURCEBOOK = "SourceBook";
  private static final String ATTRIB_NAME = "name";
  private static final String ATTRIB_EDITION = "edition";
  private static final String ATTRIB_SHOWPREREQ = "showprereqs";

  private ICharmConfiguration characterSet;

  public CharacterSourceBookFilter(ICharmConfiguration characterSet) {
    this.characterSet = characterSet;
    prepareEdition();
  }

  @Override
  protected boolean mustBeShownDueToCircumstance(ICharm charm) {
    for (ICharm learnedCharm : characterSet.getLearnedCharms(true)) {
      if (learnedCharm == charm) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void save(Element parent) {
    Element sourceBookFilter = parent.addElement(TAG_FILTERNAME);
      List<IExaltedSourceBook> list = excludedMaterial;
      if (list != null) for (IExaltedSourceBook book : list) {
        Element bookElement = sourceBookFilter.addElement(TAG_SOURCEBOOK);
        bookElement.addAttribute(ATTRIB_NAME, book.getId());
        bookElement.addAttribute(ATTRIB_EDITION, SECOND_EDITION);
      }
    sourceBookFilter.addAttribute(ATTRIB_SHOWPREREQ, includePrereqs ? "true" : "false");
  }

  @Override
  public boolean load(Element node) {
    if (node.getName().equals(TAG_FILTERNAME)) {
      for (Object bookNode : node.elements()) {
        try {
          Element sourceBook = (Element) bookNode;
          String idString = sourceBook.attributeValue(ATTRIB_NAME);
          IExaltedSourceBook book = new SourceBook(idString);
          excludedMaterial.add(book);
        } catch (Exception e) {
          excludedMaterial.clear();
          return false;
        }
      }
      includePrereqs = node.attributeValue(ATTRIB_SHOWPREREQ).equals("true");
      return true;
    }
    return false;
  }

  @Override
  protected List<ICharmGroup> getAllCharmGroups() {
    return Lists.<ICharmGroup>newArrayList(characterSet.getAllGroups());
  }
}