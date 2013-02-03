package net.sf.anathema.character.impl.module.perspective;

import com.google.common.collect.Lists;
import net.sf.anathema.character.perspective.CharacterButtonDto;
import net.sf.anathema.character.perspective.CharacterIdentifier;
import net.sf.anathema.character.perspective.CharacterStackPresenter;
import net.sf.anathema.character.perspective.CharacterSystemModel;
import net.sf.anathema.character.perspective.ToCharacterButtonDto;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.fx.character.perspective.Selector;

import javax.swing.JComponent;
import java.util.List;

public class CharacterGridPresenter {

  private final CharacterGridView view;

  public CharacterGridPresenter(CharacterGridView view) {
    this.view = view;
  }

  public void initPresentation(final IAnathemaModel model, final CharacterStackPresenter characterStack) {
    Selector<CharacterIdentifier> characterSelector = new Selector<CharacterIdentifier>() {
      @Override
      public void selected(CharacterIdentifier item) {
        characterStack.showCharacter(item);
      }
    };
    List<PrintNameFile> printNameFiles = new CharacterSystemModel(model).collectCharacterPrintNameFiles();
    List<CharacterButtonDto> dtoList = Lists.transform(printNameFiles, new ToCharacterButtonDto());
    view.addButtons(dtoList, characterSelector);
  }

  public JComponent getComponent() {
    return view.getComponent();
  }
}