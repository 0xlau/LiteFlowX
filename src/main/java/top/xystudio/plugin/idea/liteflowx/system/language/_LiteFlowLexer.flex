package top.xystudio.plugin.idea.liteflowx.system.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowTypes.*;

%%

%{
  public _LiteFlowLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _LiteFlowLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

IDENTIFIER=[a-zA-Z_][a-zA-Z\d_]*
LINE_COMMENT="//".*
BLOCK_COMMENT="/"\*\* !([^]* \*\*"/" [^]*) (\*\*"/")?
SPACE=[ \t\n\x0B\f\r]+
STRING=('([^'\\]|\\.)*'|\"([^\"\\]|\\\"|\'|\\)*\")
NUMBER=-?(0[xX][0-9a-fA-F]+|[0-9]|[1-9][0-9]*N?M?|[0-9]+(\.[0-9]+)?([Ee][+-]?[0-9]+)?M?)

%%
<YYINITIAL> {
  {WHITE_SPACE}            { return WHITE_SPACE; }

  "{"                      { return LITEFLOW_BRACE_LEFT; }
  "}"                      { return LITEFLOW_BRACE_RIGHT; }
  "["                      { return LITEFLOW_BRACK_LEFT; }
  "]"                      { return LITEFLOW_BRACK_RIGHT; }
  "("                      { return LITEFLOW_PAREN_LEFT; }
  ")"                      { return LITEFLOW_PAREN_RIGHT; }
  ":"                      { return LITEFLOW_COLON; }
  ";"                      { return LITEFLOW_SEMICOLON; }
  ","                      { return LITEFLOW_COMMA; }
  "=="                     { return LITEFLOW_EQ; }
  "="                      { return LITEFLOW_ASSIGN; }
  "!="                     { return LITEFLOW_NOT_EQ; }
  "!"                      { return LITEFLOW_NOT; }
  "++"                     { return LITEFLOW_PLUS_PLUS; }
  "+"                      { return LITEFLOW_PLUS; }
  "--"                     { return LITEFLOW_MINUS_MINUS; }
  "-"                      { return LITEFLOW_MINUS; }
  "||"                     { return LITEFLOW_COND_OR; }
  "&&"                     { return LITEFLOW_COND_AND; }
  "&"                      { return LITEFLOW_BIT_AND; }
  "|"                      { return LITEFLOW_BIT_OR; }
  "<<"                     { return LITEFLOW_SHIFT_LEFT; }
  "<="                     { return LITEFLOW_LESS_OR_EQUAL; }
  "<"                      { return LITEFLOW_LESS; }
  "^"                      { return LITEFLOW_BIT_XOR; }
  "*"                      { return LITEFLOW_MUL; }
  "/"                      { return LITEFLOW_QUOTIENT; }
  "%"                      { return LITEFLOW_REMAINDER; }
  ">>"                     { return LITEFLOW_SHIFT_RIGHT; }
  ">="                     { return LITEFLOW_GREATER_OR_EQUAL; }
  ">"                      { return LITEFLOW_GREATER; }
  "."                      { return LITEFLOW_DOT; }
  "?"                      { return LITEFLOW_QUESTION; }
  "null"                   { return LITEFLOW_NULL; }
  "true"                   { return LITEFLOW_TRUE; }
  "false"                  { return LITEFLOW_FALSE; }
  "mod"                    { return LITEFLOW_MOD; }
  "nor"                    { return LITEFLOW_NOR; }
  "in"                     { return LITEFLOW_IN; }
  "for"                    { return LITEFLOW_FOR; }
  "while"                  { return LITEFLOW_WHILE; }
  "if"                     { return LITEFLOW_IF; }
  "WHEN"                   { return LITEFLOW_WHEN; }
  "THEN"                   { return LITEFLOW_THEN; }
  "else"                   { return LITEFLOW_ELSE; }
  "exportAlias"            { return LITEFLOW_EXPORTALIAS; }
  "alias"                  { return LITEFLOW_ALIAS; }
  "break"                  { return LITEFLOW_BREAK; }
  "continue"               { return LITEFLOW_CONTINUE; }
  "return"                 { return LITEFLOW_RETURN; }
  "macro"                  { return LITEFLOW_MACRO; }
  "function"               { return LITEFLOW_FUNCTION; }
  "def"                    { return LITEFLOW_DEF; }
  "exportDef"              { return LITEFLOW_EXPORTDEF; }
  "new"                    { return LITEFLOW_NEW; }
  "array"                  { return LITEFLOW_ARRAY; }
  "anonymousNewArray"      { return LITEFLOW_ANONYMOUSNEWARRAY; }
  "like"                   { return LITEFLOW_LIKE; }
  "class"                  { return LITEFLOW_CLASS; }
  "VClass"                 { return LITEFLOW_VCLASS; }
  "cast"                   { return LITEFLOW_CAST; }
  "SWITCH"                 { return LITEFLOW_SWITCH; }
  "PRE"                    { return LITEFLOW_PRE; }
  "FINALLY"                { return LITEFLOW_FINALLY; }
  "IF"                     { return LITEFLOW_LIF; }
  "ELIF"                   { return LITEFLOW_LELIF; }
  "ELSE"                   { return LITEFLOW_LELSE; }
  "FOR"                    { return LITEFLOW_LFOR; }
  "ITERATOR"               { return LITEFLOW_LITERATOR; }
  "WHILE"                  { return LITEFLOW_LWHILE; }
  "any"                    { return LITEFLOW_ANY; }
  "id"                     { return LITEFLOW_ID; }
  "ignoreError"            { return LITEFLOW_IGNOREERROR; }
  "tag"                    { return LITEFLOW_TAG; }
  "threadPool"             { return LITEFLOW_THREADPOOL; }
  "to"                     { return LITEFLOW_TO; }
  "TO"                     { return LITEFLOW_UTO; }
  "data"                   { return LITEFLOW_DATA; }
  "node"                   { return LITEFLOW_NODE; }
  "NODE"                   { return LITEFLOW_UNODE; }
  "DO"                     { return LITEFLOW_DO; }
  "BREAK"                  { return LITEFLOW_LBREAK; }
  "DEFAULT"                { return LITEFLOW_DEFAULT; }

  {IDENTIFIER}             { return LITEFLOW_IDENTIFIER; }
  {LINE_COMMENT}           { return LITEFLOW_LINE_COMMENT; }
  {BLOCK_COMMENT}          { return LITEFLOW_BLOCK_COMMENT; }
  {SPACE}                  { return LITEFLOW_SPACE; }
  {STRING}                 { return LITEFLOW_STRING; }
  {NUMBER}                 { return LITEFLOW_NUMBER; }

}

[^] { return BAD_CHARACTER; }
