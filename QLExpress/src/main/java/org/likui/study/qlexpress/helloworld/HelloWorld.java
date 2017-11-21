package org.likui.study.qlexpress.helloworld;

import com.ql.util.express.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
    /**
     * 常规调用
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 3);
        String express = "a+b*c";
        Object r = runner.execute(express, context, null, true, false);
        System.out.println(r);
    }

    /**
     * 自定义操作符: addOperatorWithAlias + addOperator + addFunction
     */
    @Test
    public void testOperator() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        runner.addOperatorWithAlias("如果", "if", null);
        runner.addOperatorWithAlias("则", "then", null);
        runner.addOperatorWithAlias("否则", "else", null);

        String exp = "如果 1==2 则 false 否则 true"; //"如果 (如果 1=2 则 false 否则 true) 则 {2+2;} 否则 {20 + 20;}";
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        Object result = runner.execute(exp, context, null, false, false, null);

        System.out.println(result);
    }

    @Test
    public void testOperator2() throws Exception {
        ExpressRunner runner = new ExpressRunner();

        runner.addOperator("join", new JoinOperator());

        DefaultContext<String, Object> context = new DefaultContext<String, Object>();

        Object result = runner.execute("1 join 2 join 3", context, null, false, false);
        System.out.println(result);

        runner.addFunction("group", new GroupOperator("group"));
        result = runner.execute("group(1,2,3)", context, null, false, false);
        System.out.println(result);
    }

    /**
     * 类的静态方法, 对象的方法绑定 : addFunctionOfClassMethod + addFunctionOfServiceMethod
     */
    @Test
    public void testFunction() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        runner.addFunctionOfClassMethod("取绝对值", Math.class.getName(), "abs", new String[] {"double"}, null);
        runner.addFunctionOfClassMethod("转换为大写", BeanExample.class.getName(), "upper", new String[]{"String"}, null);
        runner.addFunctionOfClassMethod("打印", BeanExample.class.getName(), "println", new String[]{"String"}, null);
        runner.addFunctionOfClassMethod("contains", BeanExample.class.getName(), "anyContains", new Class[]{String.class, String.class}, null);
        String exp = "取绝对值(-1);转换为大写(\"hello world\");打印(\"你好吗\")";
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        List list = new ArrayList();
        Object result = runner.execute(exp, context, null, false, false);
        System.out.println(result);

//        exp = "转换为大写(\"hello world\")";
//        result = runner.execute(exp, context, null, false, false);
//        System.out.println(result);
    }

    /**
     * macro 宏定义
     */
    @Test
    public void testMacro() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        runner.addMacro("计算平均成绩", "(语文+数学+英语)/3.0");
        runner.addMacro("是否优秀", "计算平均成绩>90");

        IExpressContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("语文", 88);
        context.put("数学", 10);
        context.put("英语", 95);
        Object result = runner.execute("是否优秀", context, null, false, false);
        System.out.println(result);
    }

    /**
     * 编译脚本, 查询外部需要定义的变量, 注意以下脚本 int 和 没有 int 的区别
     */
    @Test
    public void testScript() throws Exception {
        String exp = "int 平均分 = (语文+数学+英语+综合考试.科目2)/4.0;return 平均分";
        ExpressRunner runner = new ExpressRunner(true, true);
        String[] names = runner.getOutVarNames(exp);
        for (String s : names) {
            System.out.println("var : " + s);
        }
    }

    /**
     * 关于不定参数的使用
     */
    @Test
    public void testMethodReplace() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        IExpressContext<String, Object> expressContext = new DefaultContext<String, Object>();
        runner.addFunctionOfServiceMethod("getTemplate", this, "getTemplate", new Class[]{Object[].class}, null);

        //默认的不定参可以用数组代替
        Object r = runner.execute("getTemplate([11, '22', 33L, true])", expressContext, null, false, false);
        System.out.println(r);

        //像java一样, 支持函数动态参数调用, 需要打开以下全局开关, 否则以下调用会失败
        DynamicParamsUtil.supportDynamicParams = true;
        r = runner.execute("getTemplate(11, '22', 33L, true)", expressContext, null, false, false);
        System.out.println(r);

    }

    /**
     * 关于集合的快捷写法
     */
    @Test
    public void testSet() throws Exception {
        ExpressRunner runner = new ExpressRunner(false, false);
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        String exp = "abc = NewMap(1:1,2:2); return abc.get(1) + abc.get(2)";
        Object r = runner.execute(exp, context, null, false, false);
        System.out.println(r);
        exp = "abc = NewList(1,2,3); return abc.get(1)+abc.get(2)";
        r = runner.execute(exp, context, null, false, false);
        System.out.println(r);
        exp = "abc = [1,2,3]; return abc[1] + abc[2];";
        r = runner.execute(exp, context, null, false,false);
        System.out.println(r);
    }

    public static Object getTemplate(Object... params) {
        String result = "";
        for (Object obj : params) {
            result = result + obj + ",";
        }
        return result;
    }

}


class JoinOperator extends Operator {

    public Object executeInner(Object[] list) throws Exception {
        Object opdata1 = list[0];
        Object opdata2 = list[1];
        if (opdata1 instanceof java.util.List) {
            ((java.util.List)opdata1).add(opdata2);
            return opdata1;
        } else {
            java.util.List result = new ArrayList();
            result.add(opdata1);
            result.add(opdata2);
            return result;
        }
    }
}

class GroupOperator extends Operator {

    private String name;

    public GroupOperator(String name) {
        this.name = name;
    }

    public Object executeInner(Object[] list) throws Exception {
        Object result = Integer.valueOf(0);
        for (int i = 0; i < list.length; i++) {
            result = OperatorOfNumber.add(result, list[i], false);
        }
        return result;
    }
}

