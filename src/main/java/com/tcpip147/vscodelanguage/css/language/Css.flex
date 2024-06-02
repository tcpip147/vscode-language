package com.tcpip147.vscodelanguage.css.language;

import com.intellij.psi.tree.IElementType;
import com.intellij.lexer.FlexLexer;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.tcpip147.vscodelanguage.css.language.psi.CssTypes.*;

%%

%{
  public CssLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class CssLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

COMMENT="/*" [^*]* "*"+ ([^/*] [^*]* "*"+)* "/"
WHITE_SPACE=[ \t\r\n\f]+

HEX=[0-9a-f]
NON_ASCII=[\240-\377]
UNICODE="\\" {HEX} {HEX}? {HEX}? {HEX}? {HEX}? {HEX}? ("\r\n" | [ \t\r\n\f])?
ESCAPE={UNICODE} | "\\" [^\r\n\f0-9a-f]
NMSTART=[_a-zI] | {NON_ASCII} | {ESCAPE}
NMCHAR=[_a-zI0-9\-] | {NON_ASCII} | {ESCAPE}
STRING1="\"" ([^\n\r\f\"] | "\\" {NEWLINE} | {ESCAPE})* "\""
STRING2="'" ([^\n\r\f\"] | "\\" {NEWLINE} | {ESCAPE})* "'"
IDENT="-"? {NMSTART} {NMCHAR}*
NAME={NMCHAR}+
NUMBER=[\-\+]? [0-9I]+ | [\-\+]? [0-9I]* "." [0-9]+
STRING={STRING1} | {STRING2}
URL=([\!#\$%&\*\-\~] | {NON_ASCII} | {ESCAPE})*
WS={WHITE_SPACE}?
NEWLINE="\n" | "\r\n" | "\r" | "\f"

ZERO_TO_FOUR_ZEROS="0"? "0"? "0"? "0"?
A="a" | "\\" {ZERO_TO_FOUR_ZEROS} ("41" | "61") ("\r\n" | [ \t\r\n\f])?
C="c" | "\\" {ZERO_TO_FOUR_ZEROS} ("43" | "63") ("\r\n" | [ \t\r\n\f])?
D="d" | "\\" {ZERO_TO_FOUR_ZEROS} ("44" | "64") ("\r\n" | [ \t\r\n\f])?
E="e" | "\\" {ZERO_TO_FOUR_ZEROS} ("45" | "65") ("\r\n" | [ \t\r\n\f])?
G="g" | "\\" {ZERO_TO_FOUR_ZEROS} ("47" | "67") ("\r\n" | [ \t\r\n\f])? | "\\g"
H="h" | "\\" {ZERO_TO_FOUR_ZEROS} ("48" | "68") ("\r\n" | [ \t\r\n\f])? | "\\h"
I="i" | "\\" {ZERO_TO_FOUR_ZEROS} ("49" | "69") ("\r\n" | [ \t\r\n\f])? | "\\i"
K="k" | "\\" {ZERO_TO_FOUR_ZEROS} ("4b" | "6b") ("\r\n" | [ \t\r\n\f])? | "\\k"
L="l" | "\\" {ZERO_TO_FOUR_ZEROS} ("4c" | "6c") ("\r\n" | [ \t\r\n\f])? | "\\l"
M="m" | "\\" {ZERO_TO_FOUR_ZEROS} ("4d" | "6d") ("\r\n" | [ \t\r\n\f])? | "\\m"
N="n" | "\\" {ZERO_TO_FOUR_ZEROS} ("4e" | "6e") ("\r\n" | [ \t\r\n\f])? | "\\n"
O="o" | "\\" {ZERO_TO_FOUR_ZEROS} ("4f" | "6f") ("\r\n" | [ \t\r\n\f])? | "\\o"
P="p" | "\\" {ZERO_TO_FOUR_ZEROS} ("50" | "70") ("\r\n" | [ \t\r\n\f])? | "\\p"
R="r" | "\\" {ZERO_TO_FOUR_ZEROS} ("52" | "72") ("\r\n" | [ \t\r\n\f])? | "\\r"
S="s" | "\\" {ZERO_TO_FOUR_ZEROS} ("53" | "73") ("\r\n" | [ \t\r\n\f])? | "\\s"
T="t" | "\\" {ZERO_TO_FOUR_ZEROS} ("54" | "74") ("\r\n" | [ \t\r\n\f])? | "\\t"
U="u" | "\\" {ZERO_TO_FOUR_ZEROS} ("55" | "75") ("\r\n" | [ \t\r\n\f])? | "\\u"
X="x" | "\\" {ZERO_TO_FOUR_ZEROS} ("58" | "78") ("\r\n" | [ \t\r\n\f])? | "\\x"
Z="z" | "\\" {ZERO_TO_FOUR_ZEROS} ("5a" | "7a") ("\r\n" | [ \t\r\n\f])? | "\\z"

LENGTH={NUMBER}{P}{X} | {NUMBER}{C}{M} | {NUMBER}{M}{M} | {NUMBER}{I}{N} | {NUMBER}{P}{T} | {NUMBER}{P}{C}
ANGLE={NUMBER}{D}{E}{G} | {NUMBER}{R}{A}{D} | {NUMBER}{G}{R}{A}{D}
TIME={NUMBER}{M}{S} | {NUMBER}{S}
FREQ={NUMBER}{H}{Z} | {NUMBER}{K}{H}{Z}
URI={U}{R}{L} "(" {WS} {STRING} {WS} ")" | {U}{R}{L} "(" {WS} {URL} {WS} ")"

%%
<YYINITIAL> {
  {WHITE_SPACE}                                                { return WHITE_SPACE; }
  {COMMENT}                                                    { return COMMENT; }
  "<!--"                                                       { return CDO; }
  "-->"                                                        { return CDC; }
  "~="                                                         { return INCLUDES; }
  "|="                                                         { return DASHMATCH; }
  {STRING}                                                     { return STRING; }
  {IDENT}                                                      { return IDENT; }
  "#" {NAME}                                                   { return HASH; }
  "@" {I}{M}{P}{O}{R}{T}                                       { return IMPORT_SYM; }
  "@" {P}{A}{G}{E}                                             { return PAGE_SYM; }
  "@" {M}{E}{D}{I}{A}                                          { return MEDIA_SYM; }
  "@charset"                                                   { return CHARSET_SYM; }
  "!" ({WS}|{COMMENT})* {I}{M}{P}{O}{R}{T}{A}{N}{T}            { return IMPORTANT_SYM; }
  {NUMBER}{E}{M}                                               { return EMS; }
  {NUMBER}{E}{X}                                               { return EXS; }
  {LENGTH}                                                     { return LENGTH; }
  {ANGLE}                                                      { return ANGLE; }
  {TIME}                                                       { return TIME; }
  {FREQ}                                                       { return FREQ; }
  {NUMBER} "%"                                                 { return PERCENTAGE; }
  {NUMBER}                                                     { return NUMBER; }
  {URI}                                                        { return URI; }
  ";"                                                          { return SEMI_COLON; }
  "{"                                                          { return BRACKET_OPEN; }
  "}"                                                          { return BRACKET_CLOSE; }
  ","                                                          { return COMMA; }
  ":"                                                          { return COLON; }
  "/"                                                          { return OP_DIVIDE; }
  "+"                                                          { return OP_PLUS; }
  ">"                                                          { return GREATER_THAN; }
  "."                                                          { return DOT; }
  "*"                                                          { return OP_MULTI; }
  "["                                                          { return SQUARE_BRACKET_OPEN; }
  "="                                                          { return OP_EQUALS; }
  "]"                                                          { return SQUARE_BRACKET_CLOSE; }
  "("                                                          { return PARENTHESES_OPEN; }
  ")"                                                          { return PARENTHESES_CLOSE; }
}

[^] { return BAD_CHARACTER; }
