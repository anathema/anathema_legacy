package net.sf.anathema.campaign.note.model;

import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.itemdata.model.IItemDescription;

public interface IBasicItemData extends IItemData {

  IItemDescription getDescription();
}