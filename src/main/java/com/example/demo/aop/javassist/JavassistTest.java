package com.example.demo.aop.javassist;

import com.example.demo.aop.asm.Base;
import org.apache.ibatis.javassist.*;
import java.io.IOException;

public class JavassistTest {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException, IOException {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.example.demo.aop.asm.Base");
        CtMethod m = cc.getDeclaredMethod("process");
        m.insertBefore("{ System.out.println(\"start\"); }");
        m.insertAfter("{ System.out.println(\"end\"); }");
        Class c = cc.toClass();
        cc.writeFile("/opt/project/demo");
        Base h = (Base)c.newInstance();
        h.process();
    }
}
