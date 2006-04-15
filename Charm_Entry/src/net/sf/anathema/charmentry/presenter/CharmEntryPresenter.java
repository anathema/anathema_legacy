package net.sf.anathema.charmentry.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.charmentry.model.CharmEntryModel;
import net.sf.anathema.charmentry.model.IConfigurableCharmData;
import net.sf.anathema.charmentry.persistence.CharmEntryPropertiesPersister;
import net.sf.anathema.charmentry.view.CharmEntryView;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.resources.IResources;

import org.dom4j.DocumentException;

public class CharmEntryPresenter {

  private final CharmEntryModel model;
  private final CharmEntryView view;
  private final IResources resources;

  public CharmEntryPresenter(CharmEntryModel model, CharmEntryView view, IResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    BasicDataPresenter basicDataPresenter = new BasicDataPresenter(model, view.addBasicDataView(), resources);
    basicDataPresenter.initPresentation();
    KeywordPresenter keywordPresenter = new KeywordPresenter(model, view.addKeywordView(), resources);
    keywordPresenter.initPresentation();
    initPersistencePresentation(basicDataPresenter, keywordPresenter);
  }

  private void initPersistencePresentation(
      final BasicDataPresenter basicDataPresenter,
      final KeywordPresenter keywordPresenter) {
    final JButton button = view.addSaveButton(resources.getString("CharmEntry.Button.Save")); //$NON-NLS-1$
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        IConfigurableCharmData charmData = model.getCharmData();
        CharacterType characterType = charmData.getCharacterType();
        try {
          CharmCache.getInstance().addCharm(charmData, model.getKeywordModel().getEntries());
          new CharmEntryPropertiesPersister().addPropertyInternal(characterType, charmData.getId(), charmData.getName());
          basicDataPresenter.charmAdded(charmData);
          keywordPresenter.charmAdded(charmData);
        }
        catch (IOException ex) {
          ex.printStackTrace();
        }
        catch (DocumentException ex) {
          ex.printStackTrace();
        }
        catch (PersistenceException ex) {
          ex.printStackTrace();
        }
      }
    });
    model.addCharmCompleteListener(new IBooleanValueChangedListener() {
      public void valueChanged(boolean newValue) {
        button.setEnabled(newValue);
      }
    });
    button.setEnabled(false);
  }
}