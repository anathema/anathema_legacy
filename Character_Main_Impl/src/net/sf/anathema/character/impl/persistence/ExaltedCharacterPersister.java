package net.sf.anathema.character.impl.persistence;

import net.disy.commons.core.message.MessageType;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.impl.magic.SpellException;
import net.sf.anathema.character.impl.model.CharacterStatisticsConfiguration;
import net.sf.anathema.character.impl.model.ExaltedCharacter;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;
import net.sf.anathema.framework.persistence.AbstractSingleFileItemPersister;
import net.sf.anathema.framework.persistence.RepositoryItemPersister;
import net.sf.anathema.framework.repository.AnathemaDataItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;
import java.io.OutputStream;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_EXALTED_CHARACTER_ROOT;

public class ExaltedCharacterPersister extends AbstractSingleFileItemPersister {

  private final RepositoryItemPersister repositoryItemPerister = new RepositoryItemPersister();
  private final CharacterDescriptionPersister descriptionPersister = new CharacterDescriptionPersister();
  private final CharacterStatisticPersister statisticsPersister;
  private final IItemType characterType;
  private final ICharacterGenerics generics;
  private final IAnathemaMessaging messaging;

  public ExaltedCharacterPersister(IItemType characterType, ICharacterGenerics generics, IAnathemaMessaging messaging) {
    this.characterType = characterType;
    this.generics = generics;
    this.messaging = messaging;
    this.statisticsPersister = new CharacterStatisticPersister(generics, messaging);
  }

  @Override
  public void save(OutputStream stream, IItem item) throws IOException {
    messaging.addMessage("CharacterPersistence.SavingCharacter", MessageType.INFORMATION, item.getDisplayName()); //$NON-NLS-1$
    Element rootElement = DocumentHelper.createElement(TAG_EXALTED_CHARACTER_ROOT);
    repositoryItemPerister.save(rootElement, item);
    save(rootElement, (ICharacter) item.getItemData());
    DocumentUtilities.save(DocumentHelper.createDocument(rootElement), stream);
  }

  private void save(Element rootElement, ICharacter character) {
    descriptionPersister.save(rootElement, character.getDescription());
    if (character.hasStatistics()) {
      statisticsPersister.save(rootElement, character.getStatistics());
    }
  }

  @Override
  public IItem load(Document characterXml) throws PersistenceException {
    Element documentRoot = characterXml.getRootElement();
    ICharacter character = new ExaltedCharacter();
    IItem item = new AnathemaDataItem(characterType, character);
    repositoryItemPerister.load(documentRoot, item);
    descriptionPersister.load(documentRoot, character.getDescription());
    statisticsPersister.load(documentRoot, character);
    markCharacterReadyForWork(character);
    return item;
  }

  public IItem createNew(IAnathemaWizardModelTemplate template) throws PersistenceException {
    if (!(template instanceof CharacterStatisticsConfiguration)) {
      throw new IllegalArgumentException("Bad template type for character creation."); //$NON-NLS-1$
    }
    CharacterStatisticsConfiguration configuration = (CharacterStatisticsConfiguration) template;
    ExaltedCharacter character = new ExaltedCharacter();
    try {
      character.createCharacterStatistics(configuration.getTemplate(), generics, configuration.getRuleSet());
      markCharacterReadyForWork(character);
      return new AnathemaDataItem(characterType, character);
    }
    catch (SpellException e) {
      throw new PersistenceException("A problem occured while creating a new character", e); //$NON-NLS-1$
    }
  }

  private void markCharacterReadyForWork(ICharacter character) {
    character.getStatistics().getCharacterContext().setFullyLoaded(true);
  }
}