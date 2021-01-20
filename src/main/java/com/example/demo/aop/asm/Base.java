package com.example.demo.aop.asm;

/**
 * 利用ASM的CoreAPI来增强类。这里不纠结于AOP的专业名词如切片、通知，只实现在方法调用前、后增加逻辑，
 * 通俗易懂且方便理解。首先定义需要被增强的Base类：其中只包含一个process()方法，方法内输出一行“process”。
 * 增强后，我们期望的是，方法执行前输出“start”，之后输出"end"。
 *
 * 定义两个类：一个是MyClassVisitor类，用于对字节码的visit以及修改；
 * 另一个是Generator类，在这个类中定义ClassReader和ClassWriter，
 * 其中的逻辑是，classReader读取字节码，然后交给MyClassVisitor类处理，
 * 处理完成后由ClassWriter写字节码并将旧的字节码替换掉。
 * Generator类较简单，我们先看一下它的实现，如下所示，然后重点解释MyClassVisitor类。
 *
 * 完成这两个visitor类后，运行Generator中的main方法完成对Base类的字节码增强，
 * 增强后的结果可以在编译后的target文件夹中找到Base.class文件进行查看，
 * 可以看到反编译后的代码已经改变了。
 * 然后写一个测试类MyTest，在其中new Base()，并调用base.process()方法，
 *
 */
public class Base {
    public void process(){
        System.out.println("process");
    }
}
