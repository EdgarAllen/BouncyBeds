package bouncy.beds;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.objectweb.asm.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class BlockBedClassVisitor extends ClassVisitor {
    public static final String targetClassName = "net.minecraft.block.BlockBed";
    private final boolean isObfuscated;

    public BlockBedClassVisitor(ClassVisitor classVisitor, boolean isObfuscated) {
        super(262144, classVisitor);
        this.isObfuscated = isObfuscated;
    }

    public static byte[] transform(byte[] bytes, boolean isObfuscated) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            ClassReader cr = new ClassReader(in);
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            BlockBedClassVisitor p = new BlockBedClassVisitor(cw, isObfuscated);

            cr.accept(p, 0);

            byte[] result = cw.toByteArray();
            in.close();
            return result;
        } catch(IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

        if(name.equals(isObfuscated ? "func_176216_a" : "onLanded") && desc.equals("(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)V")) {
            return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localOnLanded", desc, signature, exceptions);
        } else if(name.equals(isObfuscated ? "func_180658_a" : "onFallenUpon") && desc.equals("(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;F)V")) {
            return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localOnLanded", desc, signature, exceptions);
        }

        return super.visitMethod(access, name, desc, signature, exceptions);
    }

    @Override
    public void visitEnd() {
        MethodVisitor mv;
        mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "func_176216_a" : "onLanded", "(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.ALOAD, 2);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "bouncy/beds/BlockBedClassVisitor", "onLanded", "(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)V", false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        MethodVisitor mv2;
        mv2 = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "func_180658_a" : "onFallenUpon", "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;F)V", null, null);
        mv2.visitVarInsn(Opcodes.ALOAD, 0);
        mv2.visitVarInsn(Opcodes.ALOAD, 1);
        mv2.visitVarInsn(Opcodes.ALOAD, 2);
        mv2.visitVarInsn(Opcodes.ALOAD, 3);
        mv2.visitVarInsn(Opcodes.FLOAD, 4);
        mv2.visitMethodInsn(Opcodes.INVOKESTATIC, "bouncy/beds/BlockBedClassVisitor", "onFallenUpon", "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;F)V", false);
        mv2.visitInsn(Opcodes.RETURN);
        mv2.visitMaxs(0, 0);
        mv2.visitEnd();
    }

    public static void onLanded(World worldIn, Entity entityIn) {
        if(entityIn.isSneaking() || entityIn.motionY > -0.1D) {
            entityIn.motionY = 0.0D;
        } else {
            entityIn.motionY = -(entityIn.motionY * 1.24f);

            if (!(entityIn instanceof EntityLivingBase)) {
                entityIn.motionY *= 0.8D;
            }
        }
    }

    public static void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.fall(fallDistance, entityIn.isSneaking() ? 1.0f : 0.0F);
    }
}
