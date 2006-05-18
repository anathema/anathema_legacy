package net.sf.anathema.acceptance.fixture.character.persistence;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import net.sf.anathema.acceptance.fixture.character.CharacterSummary;
import net.sf.anathema.character.impl.module.ExaltedCharacterItemTypeConfiguration;
import net.sf.anathema.character.impl.persistence.ExaltedCharacterPersister;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.repository.AnathemaItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;

import fit.Fixture;
import fit.Parse;

public class SaveAndLoadCharacterFixture extends Fixture {

  private static final IItemType itemType = new ItemType(
      ExaltedCharacterItemTypeConfiguration.CHARACTER_ITEM_TYPE_ID,
      new RepositoryConfiguration(".ecg", "ExaltedCharacter/")); //$NON-NLS-1$ //$NON-NLS-2$

  @Override
  public void doTable(Parse table) {
    CharacterSummary characterSummary = new CharacterSummary(summary);
    ICharacter character = characterSummary.getCharacter();
    AnathemaItem anathemaItem = new AnathemaItem(itemType, character);
    IRepositoryItemPersister persister = new ExaltedCharacterPersister(
        itemType,
        characterSummary.getCharacterGenerics());
    anathemaItem.getRepositoryLocation().setId("TestRepositoryId"); //$NON-NLS-1$
    try {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      persister.save(new SimpleRepositoryWriteAccess(outputStream), anathemaItem);
      byte[] bytes = outputStream.toByteArray();
      ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
      IItem item = persister.load(new SimpleRepositoryReadAccess(inputStream));
      characterSummary.setCharacter((ICharacter) item.getItemData());
    }
    catch (Exception e) {
      if (e instanceof RuntimeException) {
        throw (RuntimeException) e;
      }
      throw new RuntimeException(e);
    }
  }
}