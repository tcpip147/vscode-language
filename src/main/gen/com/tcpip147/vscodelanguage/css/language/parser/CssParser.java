// This is a generated file. Not intended for manual editing.
package com.tcpip147.vscodelanguage.css.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.tcpip147.vscodelanguage.css.language.psi.CssTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class CssParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return stylesheet(b, l + 1);
  }

  /* ********************************************************** */
  // SQUARE_BRACKET_OPEN IDENT ((OP_EQUALS | INCLUDES | DASHMATCH) (IDENT | STRING))? SQUARE_BRACKET_CLOSE
  public static boolean attrib(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attrib")) return false;
    if (!nextTokenIs(b, SQUARE_BRACKET_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SQUARE_BRACKET_OPEN, IDENT);
    r = r && attrib_2(b, l + 1);
    r = r && consumeToken(b, SQUARE_BRACKET_CLOSE);
    exit_section_(b, m, ATTRIB, r);
    return r;
  }

  // ((OP_EQUALS | INCLUDES | DASHMATCH) (IDENT | STRING))?
  private static boolean attrib_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attrib_2")) return false;
    attrib_2_0(b, l + 1);
    return true;
  }

  // (OP_EQUALS | INCLUDES | DASHMATCH) (IDENT | STRING)
  private static boolean attrib_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attrib_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = attrib_2_0_0(b, l + 1);
    r = r && attrib_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // OP_EQUALS | INCLUDES | DASHMATCH
  private static boolean attrib_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attrib_2_0_0")) return false;
    boolean r;
    r = consumeToken(b, OP_EQUALS);
    if (!r) r = consumeToken(b, INCLUDES);
    if (!r) r = consumeToken(b, DASHMATCH);
    return r;
  }

  // IDENT | STRING
  private static boolean attrib_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attrib_2_0_1")) return false;
    boolean r;
    r = consumeToken(b, IDENT);
    if (!r) r = consumeToken(b, STRING);
    return r;
  }

  /* ********************************************************** */
  // DOT IDENT
  public static boolean class_$(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "class_$")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, IDENT);
    exit_section_(b, m, CLASS, r);
    return r;
  }

  /* ********************************************************** */
  // OP_PLUS | GREATER_THAN
  public static boolean combinator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "combinator")) return false;
    if (!nextTokenIs(b, "<combinator>", GREATER_THAN, OP_PLUS)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMBINATOR, "<combinator>");
    r = consumeToken(b, OP_PLUS);
    if (!r) r = consumeToken(b, GREATER_THAN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // property COLON expr prio?
  public static boolean completedDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "completedDeclaration")) return false;
    if (!nextTokenIs(b, IDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = property(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && expr(b, l + 1);
    r = r && completedDeclaration_3(b, l + 1);
    exit_section_(b, m, COMPLETED_DECLARATION, r);
    return r;
  }

  // prio?
  private static boolean completedDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "completedDeclaration_3")) return false;
    prio(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // completedDeclaration | uncompletedDeclaration
  public static boolean declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "declaration")) return false;
    if (!nextTokenIs(b, IDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = completedDeclaration(b, l + 1);
    if (!r) r = uncompletedDeclaration(b, l + 1);
    exit_section_(b, m, DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // BRACKET_OPEN declaration? (SEMI_COLON declaration?)* BRACKET_CLOSE
  public static boolean declarations(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "declarations")) return false;
    if (!nextTokenIs(b, BRACKET_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACKET_OPEN);
    r = r && declarations_1(b, l + 1);
    r = r && declarations_2(b, l + 1);
    r = r && consumeToken(b, BRACKET_CLOSE);
    exit_section_(b, m, DECLARATIONS, r);
    return r;
  }

  // declaration?
  private static boolean declarations_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "declarations_1")) return false;
    declaration(b, l + 1);
    return true;
  }

  // (SEMI_COLON declaration?)*
  private static boolean declarations_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "declarations_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!declarations_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "declarations_2", c)) break;
    }
    return true;
  }

  // SEMI_COLON declaration?
  private static boolean declarations_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "declarations_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMI_COLON);
    r = r && declarations_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // declaration?
  private static boolean declarations_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "declarations_2_0_1")) return false;
    declaration(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENT | OP_MULTI
  public static boolean element_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "element_name")) return false;
    if (!nextTokenIs(b, "<element name>", IDENT, OP_MULTI)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ELEMENT_NAME, "<element name>");
    r = consumeToken(b, IDENT);
    if (!r) r = consumeToken(b, OP_MULTI);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // term (operator? term)*
  public static boolean expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPR, "<expr>");
    r = term(b, l + 1);
    r = r && expr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (operator? term)*
  private static boolean expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!expr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "expr_1", c)) break;
    }
    return true;
  }

  // operator? term
  private static boolean expr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expr_1_0_0(b, l + 1);
    r = r && term(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // operator?
  private static boolean expr_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_1_0_0")) return false;
    operator(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENT PARENTHESES_OPEN expr PARENTHESES_CLOSE
  public static boolean function(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function")) return false;
    if (!nextTokenIs(b, IDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENT, PARENTHESES_OPEN);
    r = r && expr(b, l + 1);
    r = r && consumeToken(b, PARENTHESES_CLOSE);
    exit_section_(b, m, FUNCTION, r);
    return r;
  }

  /* ********************************************************** */
  // HASH
  public static boolean hexcolor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hexcolor")) return false;
    if (!nextTokenIs(b, HASH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HASH);
    exit_section_(b, m, HEXCOLOR, r);
    return r;
  }

  /* ********************************************************** */
  // IMPORT_SYM (STRING | URI) media_list? SEMI_COLON
  public static boolean import_$(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_$")) return false;
    if (!nextTokenIs(b, IMPORT_SYM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IMPORT_SYM);
    r = r && import_1(b, l + 1);
    r = r && import_2(b, l + 1);
    r = r && consumeToken(b, SEMI_COLON);
    exit_section_(b, m, IMPORT, r);
    return r;
  }

  // STRING | URI
  private static boolean import_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_1")) return false;
    boolean r;
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, URI);
    return r;
  }

  // media_list?
  private static boolean import_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_2")) return false;
    media_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // MEDIA_SYM media_list BRACKET_OPEN ruleset* BRACKET_CLOSE
  public static boolean media(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "media")) return false;
    if (!nextTokenIs(b, MEDIA_SYM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MEDIA_SYM);
    r = r && media_list(b, l + 1);
    r = r && consumeToken(b, BRACKET_OPEN);
    r = r && media_3(b, l + 1);
    r = r && consumeToken(b, BRACKET_CLOSE);
    exit_section_(b, m, MEDIA, r);
    return r;
  }

  // ruleset*
  private static boolean media_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "media_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ruleset(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "media_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // medium (COMMA medium)*
  public static boolean media_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "media_list")) return false;
    if (!nextTokenIs(b, IDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = medium(b, l + 1);
    r = r && media_list_1(b, l + 1);
    exit_section_(b, m, MEDIA_LIST, r);
    return r;
  }

  // (COMMA medium)*
  private static boolean media_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "media_list_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!media_list_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "media_list_1", c)) break;
    }
    return true;
  }

  // COMMA medium
  private static boolean media_list_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "media_list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && medium(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENT
  public static boolean medium(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "medium")) return false;
    if (!nextTokenIs(b, IDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENT);
    exit_section_(b, m, MEDIUM, r);
    return r;
  }

  /* ********************************************************** */
  // OP_DIVIDE | COMMA
  public static boolean operator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "operator")) return false;
    if (!nextTokenIs(b, "<operator>", COMMA, OP_DIVIDE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OPERATOR, "<operator>");
    r = consumeToken(b, OP_DIVIDE);
    if (!r) r = consumeToken(b, COMMA);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // PAGE_SYM pseudo_page? BRACKET_OPEN declaration? (SEMI_COLON declaration?)* BRACKET_CLOSE
  public static boolean page(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "page")) return false;
    if (!nextTokenIs(b, PAGE_SYM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PAGE_SYM);
    r = r && page_1(b, l + 1);
    r = r && consumeToken(b, BRACKET_OPEN);
    r = r && page_3(b, l + 1);
    r = r && page_4(b, l + 1);
    r = r && consumeToken(b, BRACKET_CLOSE);
    exit_section_(b, m, PAGE, r);
    return r;
  }

  // pseudo_page?
  private static boolean page_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "page_1")) return false;
    pseudo_page(b, l + 1);
    return true;
  }

  // declaration?
  private static boolean page_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "page_3")) return false;
    declaration(b, l + 1);
    return true;
  }

  // (SEMI_COLON declaration?)*
  private static boolean page_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "page_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!page_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "page_4", c)) break;
    }
    return true;
  }

  // SEMI_COLON declaration?
  private static boolean page_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "page_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMI_COLON);
    r = r && page_4_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // declaration?
  private static boolean page_4_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "page_4_0_1")) return false;
    declaration(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IMPORTANT_SYM
  public static boolean prio(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "prio")) return false;
    if (!nextTokenIs(b, IMPORTANT_SYM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IMPORTANT_SYM);
    exit_section_(b, m, PRIO, r);
    return r;
  }

  /* ********************************************************** */
  // IDENT
  public static boolean property(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property")) return false;
    if (!nextTokenIs(b, IDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENT);
    exit_section_(b, m, PROPERTY, r);
    return r;
  }

  /* ********************************************************** */
  // COLON (IDENT | IDENT PARENTHESES_OPEN IDENT? PARENTHESES_CLOSE)
  public static boolean pseudo(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pseudo")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && pseudo_1(b, l + 1);
    exit_section_(b, m, PSEUDO, r);
    return r;
  }

  // IDENT | IDENT PARENTHESES_OPEN IDENT? PARENTHESES_CLOSE
  private static boolean pseudo_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pseudo_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENT);
    if (!r) r = pseudo_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENT PARENTHESES_OPEN IDENT? PARENTHESES_CLOSE
  private static boolean pseudo_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pseudo_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENT, PARENTHESES_OPEN);
    r = r && pseudo_1_1_2(b, l + 1);
    r = r && consumeToken(b, PARENTHESES_CLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENT?
  private static boolean pseudo_1_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pseudo_1_1_2")) return false;
    consumeToken(b, IDENT);
    return true;
  }

  /* ********************************************************** */
  // COLON IDENT
  public static boolean pseudo_page(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pseudo_page")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COLON, IDENT);
    exit_section_(b, m, PSEUDO_PAGE, r);
    return r;
  }

  /* ********************************************************** */
  // selector (COMMA selector)* declarations
  public static boolean ruleset(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ruleset")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RULESET, "<ruleset>");
    r = selector(b, l + 1);
    r = r && ruleset_1(b, l + 1);
    r = r && declarations(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA selector)*
  private static boolean ruleset_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ruleset_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ruleset_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ruleset_1", c)) break;
    }
    return true;
  }

  // COMMA selector
  private static boolean ruleset_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ruleset_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && selector(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // simple_selector (combinator selector | (combinator? selector)?)?
  public static boolean selector(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "selector")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SELECTOR, "<selector>");
    r = simple_selector(b, l + 1);
    r = r && selector_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (combinator selector | (combinator? selector)?)?
  private static boolean selector_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "selector_1")) return false;
    selector_1_0(b, l + 1);
    return true;
  }

  // combinator selector | (combinator? selector)?
  private static boolean selector_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "selector_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = selector_1_0_0(b, l + 1);
    if (!r) r = selector_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // combinator selector
  private static boolean selector_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "selector_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = combinator(b, l + 1);
    r = r && selector(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (combinator? selector)?
  private static boolean selector_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "selector_1_0_1")) return false;
    selector_1_0_1_0(b, l + 1);
    return true;
  }

  // combinator? selector
  private static boolean selector_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "selector_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = selector_1_0_1_0_0(b, l + 1);
    r = r && selector(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // combinator?
  private static boolean selector_1_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "selector_1_0_1_0_0")) return false;
    combinator(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // element_name (HASH | class | attrib | pseudo)* | (HASH | class | attrib | pseudo)+
  public static boolean simple_selector(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_selector")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SIMPLE_SELECTOR, "<simple selector>");
    r = simple_selector_0(b, l + 1);
    if (!r) r = simple_selector_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // element_name (HASH | class | attrib | pseudo)*
  private static boolean simple_selector_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_selector_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = element_name(b, l + 1);
    r = r && simple_selector_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (HASH | class | attrib | pseudo)*
  private static boolean simple_selector_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_selector_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!simple_selector_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "simple_selector_0_1", c)) break;
    }
    return true;
  }

  // HASH | class | attrib | pseudo
  private static boolean simple_selector_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_selector_0_1_0")) return false;
    boolean r;
    r = consumeToken(b, HASH);
    if (!r) r = class_$(b, l + 1);
    if (!r) r = attrib(b, l + 1);
    if (!r) r = pseudo(b, l + 1);
    return r;
  }

  // (HASH | class | attrib | pseudo)+
  private static boolean simple_selector_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_selector_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = simple_selector_1_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!simple_selector_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "simple_selector_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // HASH | class | attrib | pseudo
  private static boolean simple_selector_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_selector_1_0")) return false;
    boolean r;
    r = consumeToken(b, HASH);
    if (!r) r = class_$(b, l + 1);
    if (!r) r = attrib(b, l + 1);
    if (!r) r = pseudo(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // (CHARSET_SYM STRING SEMI_COLON)?
  //     (CDO | CDC)* (import (CDO | CDC)*)*
  //     ((ruleset | media | page) (CDO | CDC)*)*
  static boolean stylesheet(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stylesheet")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = stylesheet_0(b, l + 1);
    r = r && stylesheet_1(b, l + 1);
    r = r && stylesheet_2(b, l + 1);
    r = r && stylesheet_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (CHARSET_SYM STRING SEMI_COLON)?
  private static boolean stylesheet_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stylesheet_0")) return false;
    stylesheet_0_0(b, l + 1);
    return true;
  }

  // CHARSET_SYM STRING SEMI_COLON
  private static boolean stylesheet_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stylesheet_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, CHARSET_SYM, STRING, SEMI_COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // (CDO | CDC)*
  private static boolean stylesheet_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stylesheet_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!stylesheet_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "stylesheet_1", c)) break;
    }
    return true;
  }

  // CDO | CDC
  private static boolean stylesheet_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stylesheet_1_0")) return false;
    boolean r;
    r = consumeToken(b, CDO);
    if (!r) r = consumeToken(b, CDC);
    return r;
  }

  // (import (CDO | CDC)*)*
  private static boolean stylesheet_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stylesheet_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!stylesheet_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "stylesheet_2", c)) break;
    }
    return true;
  }

  // import (CDO | CDC)*
  private static boolean stylesheet_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stylesheet_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = import_$(b, l + 1);
    r = r && stylesheet_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (CDO | CDC)*
  private static boolean stylesheet_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stylesheet_2_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!stylesheet_2_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "stylesheet_2_0_1", c)) break;
    }
    return true;
  }

  // CDO | CDC
  private static boolean stylesheet_2_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stylesheet_2_0_1_0")) return false;
    boolean r;
    r = consumeToken(b, CDO);
    if (!r) r = consumeToken(b, CDC);
    return r;
  }

  // ((ruleset | media | page) (CDO | CDC)*)*
  private static boolean stylesheet_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stylesheet_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!stylesheet_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "stylesheet_3", c)) break;
    }
    return true;
  }

  // (ruleset | media | page) (CDO | CDC)*
  private static boolean stylesheet_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stylesheet_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = stylesheet_3_0_0(b, l + 1);
    r = r && stylesheet_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ruleset | media | page
  private static boolean stylesheet_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stylesheet_3_0_0")) return false;
    boolean r;
    r = ruleset(b, l + 1);
    if (!r) r = media(b, l + 1);
    if (!r) r = page(b, l + 1);
    return r;
  }

  // (CDO | CDC)*
  private static boolean stylesheet_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stylesheet_3_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!stylesheet_3_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "stylesheet_3_0_1", c)) break;
    }
    return true;
  }

  // CDO | CDC
  private static boolean stylesheet_3_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stylesheet_3_0_1_0")) return false;
    boolean r;
    r = consumeToken(b, CDO);
    if (!r) r = consumeToken(b, CDC);
    return r;
  }

  /* ********************************************************** */
  // (NUMBER | PERCENTAGE | LENGTH | EMS | EXS | ANGLE | TIME | FREQ) | STRING | IDENT | URI | hexcolor | function
  public static boolean term(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "term")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TERM, "<term>");
    r = term_0(b, l + 1);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, IDENT);
    if (!r) r = consumeToken(b, URI);
    if (!r) r = hexcolor(b, l + 1);
    if (!r) r = function(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // NUMBER | PERCENTAGE | LENGTH | EMS | EXS | ANGLE | TIME | FREQ
  private static boolean term_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "term_0")) return false;
    boolean r;
    r = consumeToken(b, NUMBER);
    if (!r) r = consumeToken(b, PERCENTAGE);
    if (!r) r = consumeToken(b, LENGTH);
    if (!r) r = consumeToken(b, EMS);
    if (!r) r = consumeToken(b, EXS);
    if (!r) r = consumeToken(b, ANGLE);
    if (!r) r = consumeToken(b, TIME);
    if (!r) r = consumeToken(b, FREQ);
    return r;
  }

  /* ********************************************************** */
  // IDENT
  public static boolean uncompletedDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "uncompletedDeclaration")) return false;
    if (!nextTokenIs(b, IDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENT);
    exit_section_(b, m, UNCOMPLETED_DECLARATION, r);
    return r;
  }

}
