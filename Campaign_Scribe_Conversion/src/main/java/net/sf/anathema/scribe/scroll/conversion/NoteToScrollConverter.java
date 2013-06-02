package net.sf.anathema.scribe.scroll.conversion;

import net.sf.anathema.campaign.module.NoteTypeConfiguration;
import net.sf.anathema.campaign.note.model.BasicItemData;
import net.sf.anathema.campaign.note.persistence.BasicDataItemPersister;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.styledtext.model.IStyledTextualDescription;
import net.sf.anathema.scribe.scroll.persistence.ScrollDto;

public class NoteToScrollConverter {
  public ScrollDto convert(IRepositoryReadAccess access) {
    BasicDataItemPersister persister = new BasicDataItemPersister(NoteTypeConfiguration.ITEM_TYPE, "Note");
    IItem item = persister.load(access);
    BasicItemData itemData = (BasicItemData) item.getItemData();
    String title = getTitle(itemData);
    String wikiText = getWikiText(itemData);
    return new ScrollDto(title, wikiText);
  }

  private String getTitle(BasicItemData itemData) {
    return itemData.getDescription().getName().getText();
  }

  private String getWikiText(BasicItemData itemData) {
    IStyledTextualDescription content = itemData.getDescription().getContent();
    return new StyledTextToWikiTextConverter().convert(content);
  }
}