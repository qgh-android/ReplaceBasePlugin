package com.power.plugin.apm;


import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class PowerMonitorClassVisitor extends ClassVisitor implements Opcodes {

    private String mClassName;

    public PowerMonitorClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM7, cv);
    }


    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
//        String inter = Arrays.toString(interfaces);
//        System.out.println(String.format("ClassVisitor#visit: version=%d,access=%d,name=%s,signature=%s,superName=%s,interfaces=%s",
//                version, access, name, signature, superName, inter));
//        this.mClassName = name;

        //replace extends Activity (exclude androidx#ComponentActivity)
        if ("android/app/Activity".equals(superName) &&
                !name.equals("androidx/core/app/ComponentActivity") &&
                !name.equals("com/gavin/asmdemo/BaseActivity")) {

            superName = "com/gavin/asmdemo/BaseActivity";
            System.out.println("replace ? extends Activity, name=" + name);
        }
        //replace extends androidx#ComponentActivity,
        if ("androidx/activity/ComponentActivity".equals(superName) &&
                !name.equals("com/gavin/asmdemo/BaseComponentActivity")) {

            superName = "com/gavin/asmdemo/BaseComponentActivity";
            System.out.println("replace ? extends ComponentActivity, name=" + name);
        }
        //replace extends Application
        if ("android/app/Application".equals(superName) &&
                !"com/gavin/asmdemo/BaseApplication".equals(name)) {

            superName = "com/gavin/asmdemo/BaseApplication";
            System.out.println("replace ? extends Application, name=" + name);
        }
        super.visit(version, access, name, signature, superName, interfaces);
    }


}
