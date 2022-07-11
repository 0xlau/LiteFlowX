// This is a generated file. Not intended for manual editing.
package top.xystudio.plugin.idea.liteflowx.system.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.impl.*;

public interface LiteFlowTypes {

  IElementType LITEFLOW_ALL_EXPRESS = new LiteFlowElementType("LITEFLOW_ALL_EXPRESS");
  IElementType LITEFLOW_ARRAY_EXPRESS = new LiteFlowElementType("LITEFLOW_ARRAY_EXPRESS");
  IElementType LITEFLOW_ASSIGN_EXPRESS = new LiteFlowElementType("LITEFLOW_ASSIGN_EXPRESS");
  IElementType LITEFLOW_ASSIGN_STATEMENT = new LiteFlowElementType("LITEFLOW_ASSIGN_STATEMENT");
  IElementType LITEFLOW_BOOLEAN = new LiteFlowElementType("LITEFLOW_BOOLEAN");
  IElementType LITEFLOW_BREAK_STATEMENT = new LiteFlowElementType("LITEFLOW_BREAK_STATEMENT");
  IElementType LITEFLOW_CODES = new LiteFlowElementType("LITEFLOW_CODES");
  IElementType LITEFLOW_CONTINUE_STATEMENT = new LiteFlowElementType("LITEFLOW_CONTINUE_STATEMENT");
  IElementType LITEFLOW_EXPRESS = new LiteFlowElementType("LITEFLOW_EXPRESS");
  IElementType LITEFLOW_FN_INVOKE_EXPRESS = new LiteFlowElementType("LITEFLOW_FN_INVOKE_EXPRESS");
  IElementType LITEFLOW_FN_INVOKE_STATEMENT = new LiteFlowElementType("LITEFLOW_FN_INVOKE_STATEMENT");
  IElementType LITEFLOW_FN_PARAM_EXPRESS = new LiteFlowElementType("LITEFLOW_FN_PARAM_EXPRESS");
  IElementType LITEFLOW_FOR_STATEMENT = new LiteFlowElementType("LITEFLOW_FOR_STATEMENT");
  IElementType LITEFLOW_IF_STATEMENT = new LiteFlowElementType("LITEFLOW_IF_STATEMENT");
  IElementType LITEFLOW_LITE_FLOW_ALL_EXPRESS = new LiteFlowElementType("LITEFLOW_LITE_FLOW_ALL_EXPRESS");
  IElementType LITEFLOW_LITE_FLOW_ANY_EXPRESS = new LiteFlowElementType("LITEFLOW_LITE_FLOW_ANY_EXPRESS");
  IElementType LITEFLOW_LITE_FLOW_CONDITION_EXPRESS = new LiteFlowElementType("LITEFLOW_LITE_FLOW_CONDITION_EXPRESS");
  IElementType LITEFLOW_LITE_FLOW_FINALLY_EXPRESS = new LiteFlowElementType("LITEFLOW_LITE_FLOW_FINALLY_EXPRESS");
  IElementType LITEFLOW_LITE_FLOW_ID_EXPRESS = new LiteFlowElementType("LITEFLOW_LITE_FLOW_ID_EXPRESS");
  IElementType LITEFLOW_LITE_FLOW_IGNORE_ERROR_EXPRESS = new LiteFlowElementType("LITEFLOW_LITE_FLOW_IGNORE_ERROR_EXPRESS");
  IElementType LITEFLOW_LITE_FLOW_NODE_REF = new LiteFlowElementType("LITEFLOW_LITE_FLOW_NODE_REF");
  IElementType LITEFLOW_LITE_FLOW_PRE_EXPRESS = new LiteFlowElementType("LITEFLOW_LITE_FLOW_PRE_EXPRESS");
  IElementType LITEFLOW_LITE_FLOW_STATEMENT = new LiteFlowElementType("LITEFLOW_LITE_FLOW_STATEMENT");
  IElementType LITEFLOW_LITE_FLOW_SWITCH_EXPRESS = new LiteFlowElementType("LITEFLOW_LITE_FLOW_SWITCH_EXPRESS");
  IElementType LITEFLOW_LITE_FLOW_TAG_EXPRESS = new LiteFlowElementType("LITEFLOW_LITE_FLOW_TAG_EXPRESS");
  IElementType LITEFLOW_LITE_FLOW_THEN_EXPRESS = new LiteFlowElementType("LITEFLOW_LITE_FLOW_THEN_EXPRESS");
  IElementType LITEFLOW_LITE_FLOW_THREAD_POOL_EXPRESS = new LiteFlowElementType("LITEFLOW_LITE_FLOW_THREAD_POOL_EXPRESS");
  IElementType LITEFLOW_LITE_FLOW_TO_EXPRESS = new LiteFlowElementType("LITEFLOW_LITE_FLOW_TO_EXPRESS");
  IElementType LITEFLOW_LITE_FLOW_WHEN_EXPRESS = new LiteFlowElementType("LITEFLOW_LITE_FLOW_WHEN_EXPRESS");
  IElementType LITEFLOW_NEW_EXPRESS = new LiteFlowElementType("LITEFLOW_NEW_EXPRESS");
  IElementType LITEFLOW_NEW_STATEMENT = new LiteFlowElementType("LITEFLOW_NEW_STATEMENT");
  IElementType LITEFLOW_OP = new LiteFlowElementType("LITEFLOW_OP");
  IElementType LITEFLOW_OP_EXPRESS = new LiteFlowElementType("LITEFLOW_OP_EXPRESS");
  IElementType LITEFLOW_OP_SELF_EXPRESS = new LiteFlowElementType("LITEFLOW_OP_SELF_EXPRESS");
  IElementType LITEFLOW_OP_SELF_STATEMENT = new LiteFlowElementType("LITEFLOW_OP_SELF_STATEMENT");
  IElementType LITEFLOW_REF_EXPRESS = new LiteFlowElementType("LITEFLOW_REF_EXPRESS");
  IElementType LITEFLOW_RETURN_STATEMENT = new LiteFlowElementType("LITEFLOW_RETURN_STATEMENT");
  IElementType LITEFLOW_STATEMENT = new LiteFlowElementType("LITEFLOW_STATEMENT");
  IElementType LITEFLOW_SUB_ALL_EXPRESS = new LiteFlowElementType("LITEFLOW_SUB_ALL_EXPRESS");
  IElementType LITEFLOW_THREE_EXPRESS = new LiteFlowElementType("LITEFLOW_THREE_EXPRESS");
  IElementType LITEFLOW_THREE_STATEMENT = new LiteFlowElementType("LITEFLOW_THREE_STATEMENT");
  IElementType LITEFLOW_TYPE_REF = new LiteFlowElementType("LITEFLOW_TYPE_REF");
  IElementType LITEFLOW_USE_ARRAY_EXPRESS = new LiteFlowElementType("LITEFLOW_USE_ARRAY_EXPRESS");
  IElementType LITEFLOW_VALUE = new LiteFlowElementType("LITEFLOW_VALUE");
  IElementType LITEFLOW_WHILE_STATEMENT = new LiteFlowElementType("LITEFLOW_WHILE_STATEMENT");

  IElementType LITEFLOW_ALIAS = new LiteFlowTokenType("alias");
  IElementType LITEFLOW_ANONYMOUSNEWARRAY = new LiteFlowTokenType("anonymousNewArray");
  IElementType LITEFLOW_ANY = new LiteFlowTokenType("any");
  IElementType LITEFLOW_ARRAY = new LiteFlowTokenType("array");
  IElementType LITEFLOW_ASSIGN = new LiteFlowTokenType("=");
  IElementType LITEFLOW_BIT_AND = new LiteFlowTokenType("&");
  IElementType LITEFLOW_BIT_OR = new LiteFlowTokenType("|");
  IElementType LITEFLOW_BIT_XOR = new LiteFlowTokenType("^");
  IElementType LITEFLOW_BLOCK_COMMENT = new LiteFlowTokenType("block_comment");
  IElementType LITEFLOW_BRACE_LEFT = new LiteFlowTokenType("{");
  IElementType LITEFLOW_BRACE_RIGHT = new LiteFlowTokenType("}");
  IElementType LITEFLOW_BRACK_LEFT = new LiteFlowTokenType("[");
  IElementType LITEFLOW_BRACK_RIGHT = new LiteFlowTokenType("]");
  IElementType LITEFLOW_BREAK = new LiteFlowTokenType("break");
  IElementType LITEFLOW_CAST = new LiteFlowTokenType("cast");
  IElementType LITEFLOW_CLASS = new LiteFlowTokenType("class");
  IElementType LITEFLOW_COLON = new LiteFlowTokenType(":");
  IElementType LITEFLOW_COMMA = new LiteFlowTokenType(",");
  IElementType LITEFLOW_COND_AND = new LiteFlowTokenType("&&");
  IElementType LITEFLOW_COND_OR = new LiteFlowTokenType("||");
  IElementType LITEFLOW_CONTINUE = new LiteFlowTokenType("continue");
  IElementType LITEFLOW_DEF = new LiteFlowTokenType("def");
  IElementType LITEFLOW_DOT = new LiteFlowTokenType(".");
  IElementType LITEFLOW_ELSE = new LiteFlowTokenType("else");
  IElementType LITEFLOW_EQ = new LiteFlowTokenType("==");
  IElementType LITEFLOW_EXPORTALIAS = new LiteFlowTokenType("exportAlias");
  IElementType LITEFLOW_EXPORTDEF = new LiteFlowTokenType("exportDef");
  IElementType LITEFLOW_FALSE = new LiteFlowTokenType("false");
  IElementType LITEFLOW_FINALLY = new LiteFlowTokenType("FINALLY");
  IElementType LITEFLOW_FOR = new LiteFlowTokenType("for");
  IElementType LITEFLOW_FUNCTION = new LiteFlowTokenType("function");
  IElementType LITEFLOW_GREATER = new LiteFlowTokenType(">");
  IElementType LITEFLOW_GREATER_OR_EQUAL = new LiteFlowTokenType(">=");
  IElementType LITEFLOW_ID = new LiteFlowTokenType("id");
  IElementType LITEFLOW_IDENTIFIER = new LiteFlowTokenType("identifier");
  IElementType LITEFLOW_IF = new LiteFlowTokenType("if");
  IElementType LITEFLOW_IGNOREERROR = new LiteFlowTokenType("ignoreError");
  IElementType LITEFLOW_IN = new LiteFlowTokenType("in");
  IElementType LITEFLOW_LESS = new LiteFlowTokenType("<");
  IElementType LITEFLOW_LESS_OR_EQUAL = new LiteFlowTokenType("<=");
  IElementType LITEFLOW_LIKE = new LiteFlowTokenType("like");
  IElementType LITEFLOW_MACRO = new LiteFlowTokenType("macro");
  IElementType LITEFLOW_MINUS = new LiteFlowTokenType("-");
  IElementType LITEFLOW_MINUS_MINUS = new LiteFlowTokenType("--");
  IElementType LITEFLOW_MOD = new LiteFlowTokenType("mod");
  IElementType LITEFLOW_MUL = new LiteFlowTokenType("*");
  IElementType LITEFLOW_NEW = new LiteFlowTokenType("new");
  IElementType LITEFLOW_NOR = new LiteFlowTokenType("nor");
  IElementType LITEFLOW_NOT = new LiteFlowTokenType("!");
  IElementType LITEFLOW_NOT_EQ = new LiteFlowTokenType("!=");
  IElementType LITEFLOW_NULL = new LiteFlowTokenType("null");
  IElementType LITEFLOW_NUMBER = new LiteFlowTokenType("number");
  IElementType LITEFLOW_PAREN_LEFT = new LiteFlowTokenType("(");
  IElementType LITEFLOW_PAREN_RIGHT = new LiteFlowTokenType(")");
  IElementType LITEFLOW_PLUS = new LiteFlowTokenType("+");
  IElementType LITEFLOW_PLUS_PLUS = new LiteFlowTokenType("++");
  IElementType LITEFLOW_PRE = new LiteFlowTokenType("PRE");
  IElementType LITEFLOW_QUESTION = new LiteFlowTokenType("?");
  IElementType LITEFLOW_QUOTIENT = new LiteFlowTokenType("/");
  IElementType LITEFLOW_REMAINDER = new LiteFlowTokenType("%");
  IElementType LITEFLOW_RETURN = new LiteFlowTokenType("return");
  IElementType LITEFLOW_SEMICOLON = new LiteFlowTokenType(";");
  IElementType LITEFLOW_SHIFT_LEFT = new LiteFlowTokenType("<<");
  IElementType LITEFLOW_SHIFT_RIGHT = new LiteFlowTokenType(">>");
  IElementType LITEFLOW_STRING = new LiteFlowTokenType("string");
  IElementType LITEFLOW_SWITCH = new LiteFlowTokenType("SWITCH");
  IElementType LITEFLOW_TAG = new LiteFlowTokenType("tag");
  IElementType LITEFLOW_THEN = new LiteFlowTokenType("THEN");
  IElementType LITEFLOW_THREADPOOL = new LiteFlowTokenType("threadPool");
  IElementType LITEFLOW_TO = new LiteFlowTokenType("to");
  IElementType LITEFLOW_TRUE = new LiteFlowTokenType("true");
  IElementType LITEFLOW_VCLASS = new LiteFlowTokenType("VClass");
  IElementType LITEFLOW_WHEN = new LiteFlowTokenType("WHEN");
  IElementType LITEFLOW_WHILE = new LiteFlowTokenType("while");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == LITEFLOW_ALL_EXPRESS) {
        return new LiteFlowAllExpressImpl(node);
      }
      else if (type == LITEFLOW_ARRAY_EXPRESS) {
        return new LiteFlowArrayExpressImpl(node);
      }
      else if (type == LITEFLOW_ASSIGN_EXPRESS) {
        return new LiteFlowAssignExpressImpl(node);
      }
      else if (type == LITEFLOW_ASSIGN_STATEMENT) {
        return new LiteFlowAssignStatementImpl(node);
      }
      else if (type == LITEFLOW_BOOLEAN) {
        return new LiteFlowBooleanImpl(node);
      }
      else if (type == LITEFLOW_BREAK_STATEMENT) {
        return new LiteFlowBreakStatementImpl(node);
      }
      else if (type == LITEFLOW_CODES) {
        return new LiteFlowCodesImpl(node);
      }
      else if (type == LITEFLOW_CONTINUE_STATEMENT) {
        return new LiteFlowContinueStatementImpl(node);
      }
      else if (type == LITEFLOW_EXPRESS) {
        return new LiteFlowExpressImpl(node);
      }
      else if (type == LITEFLOW_FN_INVOKE_EXPRESS) {
        return new LiteFlowFnInvokeExpressImpl(node);
      }
      else if (type == LITEFLOW_FN_INVOKE_STATEMENT) {
        return new LiteFlowFnInvokeStatementImpl(node);
      }
      else if (type == LITEFLOW_FN_PARAM_EXPRESS) {
        return new LiteFlowFnParamExpressImpl(node);
      }
      else if (type == LITEFLOW_FOR_STATEMENT) {
        return new LiteFlowForStatementImpl(node);
      }
      else if (type == LITEFLOW_IF_STATEMENT) {
        return new LiteFlowIfStatementImpl(node);
      }
      else if (type == LITEFLOW_LITE_FLOW_ALL_EXPRESS) {
        return new LiteFlowLiteFlowAllExpressImpl(node);
      }
      else if (type == LITEFLOW_LITE_FLOW_ANY_EXPRESS) {
        return new LiteFlowLiteFlowAnyExpressImpl(node);
      }
      else if (type == LITEFLOW_LITE_FLOW_CONDITION_EXPRESS) {
        return new LiteFlowLiteFlowConditionExpressImpl(node);
      }
      else if (type == LITEFLOW_LITE_FLOW_FINALLY_EXPRESS) {
        return new LiteFlowLiteFlowFinallyExpressImpl(node);
      }
      else if (type == LITEFLOW_LITE_FLOW_ID_EXPRESS) {
        return new LiteFlowLiteFlowIdExpressImpl(node);
      }
      else if (type == LITEFLOW_LITE_FLOW_IGNORE_ERROR_EXPRESS) {
        return new LiteFlowLiteFlowIgnoreErrorExpressImpl(node);
      }
      else if (type == LITEFLOW_LITE_FLOW_NODE_REF) {
        return new LiteFlowLiteFlowNodeRefImpl(node);
      }
      else if (type == LITEFLOW_LITE_FLOW_PRE_EXPRESS) {
        return new LiteFlowLiteFlowPreExpressImpl(node);
      }
      else if (type == LITEFLOW_LITE_FLOW_STATEMENT) {
        return new LiteFlowLiteFlowStatementImpl(node);
      }
      else if (type == LITEFLOW_LITE_FLOW_SWITCH_EXPRESS) {
        return new LiteFlowLiteFlowSwitchExpressImpl(node);
      }
      else if (type == LITEFLOW_LITE_FLOW_TAG_EXPRESS) {
        return new LiteFlowLiteFlowTagExpressImpl(node);
      }
      else if (type == LITEFLOW_LITE_FLOW_THEN_EXPRESS) {
        return new LiteFlowLiteFlowThenExpressImpl(node);
      }
      else if (type == LITEFLOW_LITE_FLOW_THREAD_POOL_EXPRESS) {
        return new LiteFlowLiteFlowThreadPoolExpressImpl(node);
      }
      else if (type == LITEFLOW_LITE_FLOW_TO_EXPRESS) {
        return new LiteFlowLiteFlowToExpressImpl(node);
      }
      else if (type == LITEFLOW_LITE_FLOW_WHEN_EXPRESS) {
        return new LiteFlowLiteFlowWhenExpressImpl(node);
      }
      else if (type == LITEFLOW_NEW_EXPRESS) {
        return new LiteFlowNewExpressImpl(node);
      }
      else if (type == LITEFLOW_NEW_STATEMENT) {
        return new LiteFlowNewStatementImpl(node);
      }
      else if (type == LITEFLOW_OP) {
        return new LiteFlowOpImpl(node);
      }
      else if (type == LITEFLOW_OP_EXPRESS) {
        return new LiteFlowOpExpressImpl(node);
      }
      else if (type == LITEFLOW_OP_SELF_EXPRESS) {
        return new LiteFlowOpSelfExpressImpl(node);
      }
      else if (type == LITEFLOW_OP_SELF_STATEMENT) {
        return new LiteFlowOpSelfStatementImpl(node);
      }
      else if (type == LITEFLOW_REF_EXPRESS) {
        return new LiteFlowRefExpressImpl(node);
      }
      else if (type == LITEFLOW_RETURN_STATEMENT) {
        return new LiteFlowReturnStatementImpl(node);
      }
      else if (type == LITEFLOW_STATEMENT) {
        return new LiteFlowStatementImpl(node);
      }
      else if (type == LITEFLOW_SUB_ALL_EXPRESS) {
        return new LiteFlowSubAllExpressImpl(node);
      }
      else if (type == LITEFLOW_THREE_EXPRESS) {
        return new LiteFlowThreeExpressImpl(node);
      }
      else if (type == LITEFLOW_THREE_STATEMENT) {
        return new LiteFlowThreeStatementImpl(node);
      }
      else if (type == LITEFLOW_TYPE_REF) {
        return new LiteFlowTypeRefImpl(node);
      }
      else if (type == LITEFLOW_USE_ARRAY_EXPRESS) {
        return new LiteFlowUseArrayExpressImpl(node);
      }
      else if (type == LITEFLOW_VALUE) {
        return new LiteFlowValueImpl(node);
      }
      else if (type == LITEFLOW_WHILE_STATEMENT) {
        return new LiteFlowWhileStatementImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
