// This is a generated file. Not intended for manual editing.
package com.tcpip147.vscodelanguage.css.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.tcpip147.vscodelanguage.css.language.psi.impl.*;

public interface CssTypes {

  IElementType ATTRIB = new CssElementType("ATTRIB");
  IElementType CLASS = new CssElementType("CLASS");
  IElementType COMBINATOR = new CssElementType("COMBINATOR");
  IElementType COMPLETED_DECLARATION = new CssElementType("COMPLETED_DECLARATION");
  IElementType DECLARATION = new CssElementType("DECLARATION");
  IElementType DECLARATIONS = new CssElementType("DECLARATIONS");
  IElementType ELEMENT_NAME = new CssElementType("ELEMENT_NAME");
  IElementType EXPR = new CssElementType("EXPR");
  IElementType FUNCTION = new CssElementType("FUNCTION");
  IElementType HEXCOLOR = new CssElementType("HEXCOLOR");
  IElementType IMPORT = new CssElementType("IMPORT");
  IElementType MEDIA = new CssElementType("MEDIA");
  IElementType MEDIA_LIST = new CssElementType("MEDIA_LIST");
  IElementType MEDIUM = new CssElementType("MEDIUM");
  IElementType OPERATOR = new CssElementType("OPERATOR");
  IElementType PAGE = new CssElementType("PAGE");
  IElementType PRIO = new CssElementType("PRIO");
  IElementType PROPERTY = new CssElementType("PROPERTY");
  IElementType PSEUDO = new CssElementType("PSEUDO");
  IElementType PSEUDO_PAGE = new CssElementType("PSEUDO_PAGE");
  IElementType RULESET = new CssElementType("RULESET");
  IElementType SELECTOR = new CssElementType("SELECTOR");
  IElementType SIMPLE_SELECTOR = new CssElementType("SIMPLE_SELECTOR");
  IElementType TERM = new CssElementType("TERM");
  IElementType UNCOMPLETED_DECLARATION = new CssElementType("UNCOMPLETED_DECLARATION");

  IElementType ANGLE = new CssTokenType("ANGLE");
  IElementType BRACKET_CLOSE = new CssTokenType("BRACKET_CLOSE");
  IElementType BRACKET_OPEN = new CssTokenType("BRACKET_OPEN");
  IElementType CDC = new CssTokenType("CDC");
  IElementType CDO = new CssTokenType("CDO");
  IElementType CHARSET_SYM = new CssTokenType("CHARSET_SYM");
  IElementType COLON = new CssTokenType("COLON");
  IElementType COMMA = new CssTokenType("COMMA");
  IElementType COMMENT = new CssTokenType("COMMENT");
  IElementType DASHMATCH = new CssTokenType("DASHMATCH");
  IElementType DOT = new CssTokenType("DOT");
  IElementType EMS = new CssTokenType("EMS");
  IElementType EXS = new CssTokenType("EXS");
  IElementType FREQ = new CssTokenType("FREQ");
  IElementType GREATER_THAN = new CssTokenType("GREATER_THAN");
  IElementType HASH = new CssTokenType("HASH");
  IElementType IDENT = new CssTokenType("IDENT");
  IElementType IMPORTANT_SYM = new CssTokenType("IMPORTANT_SYM");
  IElementType IMPORT_SYM = new CssTokenType("IMPORT_SYM");
  IElementType INCLUDES = new CssTokenType("INCLUDES");
  IElementType LENGTH = new CssTokenType("LENGTH");
  IElementType MEDIA_SYM = new CssTokenType("MEDIA_SYM");
  IElementType NUMBER = new CssTokenType("NUMBER");
  IElementType OP_DIVIDE = new CssTokenType("OP_DIVIDE");
  IElementType OP_EQUALS = new CssTokenType("OP_EQUALS");
  IElementType OP_MULTI = new CssTokenType("OP_MULTI");
  IElementType OP_PLUS = new CssTokenType("OP_PLUS");
  IElementType PAGE_SYM = new CssTokenType("PAGE_SYM");
  IElementType PARENTHESES_CLOSE = new CssTokenType("PARENTHESES_CLOSE");
  IElementType PARENTHESES_OPEN = new CssTokenType("PARENTHESES_OPEN");
  IElementType PERCENTAGE = new CssTokenType("PERCENTAGE");
  IElementType SEMI_COLON = new CssTokenType("SEMI_COLON");
  IElementType SQUARE_BRACKET_CLOSE = new CssTokenType("SQUARE_BRACKET_CLOSE");
  IElementType SQUARE_BRACKET_OPEN = new CssTokenType("SQUARE_BRACKET_OPEN");
  IElementType STRING = new CssTokenType("STRING");
  IElementType TIME = new CssTokenType("TIME");
  IElementType URI = new CssTokenType("URI");
  IElementType WHITE_SPACE = new CssTokenType("WHITE_SPACE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ATTRIB) {
        return new CssAttribImpl(node);
      }
      else if (type == CLASS) {
        return new CssClassImpl(node);
      }
      else if (type == COMBINATOR) {
        return new CssCombinatorImpl(node);
      }
      else if (type == COMPLETED_DECLARATION) {
        return new CssCompletedDeclarationImpl(node);
      }
      else if (type == DECLARATION) {
        return new CssDeclarationImpl(node);
      }
      else if (type == DECLARATIONS) {
        return new CssDeclarationsImpl(node);
      }
      else if (type == ELEMENT_NAME) {
        return new CssElementNameImpl(node);
      }
      else if (type == EXPR) {
        return new CssExprImpl(node);
      }
      else if (type == FUNCTION) {
        return new CssFunctionImpl(node);
      }
      else if (type == HEXCOLOR) {
        return new CssHexcolorImpl(node);
      }
      else if (type == IMPORT) {
        return new CssImportImpl(node);
      }
      else if (type == MEDIA) {
        return new CssMediaImpl(node);
      }
      else if (type == MEDIA_LIST) {
        return new CssMediaListImpl(node);
      }
      else if (type == MEDIUM) {
        return new CssMediumImpl(node);
      }
      else if (type == OPERATOR) {
        return new CssOperatorImpl(node);
      }
      else if (type == PAGE) {
        return new CssPageImpl(node);
      }
      else if (type == PRIO) {
        return new CssPrioImpl(node);
      }
      else if (type == PROPERTY) {
        return new CssPropertyImpl(node);
      }
      else if (type == PSEUDO) {
        return new CssPseudoImpl(node);
      }
      else if (type == PSEUDO_PAGE) {
        return new CssPseudoPageImpl(node);
      }
      else if (type == RULESET) {
        return new CssRulesetImpl(node);
      }
      else if (type == SELECTOR) {
        return new CssSelectorImpl(node);
      }
      else if (type == SIMPLE_SELECTOR) {
        return new CssSimpleSelectorImpl(node);
      }
      else if (type == TERM) {
        return new CssTermImpl(node);
      }
      else if (type == UNCOMPLETED_DECLARATION) {
        return new CssUncompletedDeclarationImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
