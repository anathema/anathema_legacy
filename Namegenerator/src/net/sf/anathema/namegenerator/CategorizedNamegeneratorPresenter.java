package net.sf.anathema.namegenerator;

import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.namegenerator.domain.category.AggregatedTokenCategory;
import net.sf.anathema.namegenerator.domain.category.ICategorizedTokenConfiguration;
import net.sf.anathema.namegenerator.domain.category.TokenCategory;
import net.sf.anathema.namegenerator.view.category.ICategorizedNameGeneratorView;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

public class CategorizedNamegeneratorPresenter implements Presenter {

  private final ICategorizedNameGeneratorView view;
  private final ICategorizedTokenConfiguration tokenRegistry;

  public CategorizedNamegeneratorPresenter(
      ICategorizedNameGeneratorView view,
      ICategorizedTokenConfiguration tokenRegistry) {
    this.view = view;
    this.tokenRegistry = tokenRegistry;
  }

  @Override
  public void initPresentation() {
    Object[] categories = getAllVoidStateCategories();
    ListCellRenderer renderer = new DefaultListCellRenderer() {

	  @Override
      public Component getListCellRendererComponent(
          JList list,
          Object value,
          int index,
          boolean isSelected,
          boolean cellHasFocus) {
        if (value == null) {
          value = "Select";
        }
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
      }
    };
    view.initGui(categories, 5, renderer);
  }

  private TokenCategory[] getAllVoidStateCategories() {
    List<TokenCategory> tokenCategories = new ArrayList<>();
    tokenCategories.add(null);
    for (TokenCategory category : tokenRegistry.getRootTokenCategories()) {
      addAllRelevantTokens(category, tokenCategories);
    }
    return tokenCategories.toArray(new TokenCategory[tokenCategories.size()]);
  }

  private void addAllRelevantTokens(TokenCategory category, List<TokenCategory> tokenCategories) {
    tokenCategories.add(category);
    if (category instanceof AggregatedTokenCategory) {
      for (TokenCategory subCategory : ((AggregatedTokenCategory) category).getSubCateogories()) {
        addAllRelevantTokens(subCategory, tokenCategories);
      }
    }
  }
}
