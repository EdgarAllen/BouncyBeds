package bouncy.beds;

import net.minecraft.launchwrapper.IClassTransformer;

public class BouncyBedsTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if(transformedName.equals(BlockBedClassVisitor.targetClassName)) {
            return BlockBedClassVisitor.transform(bytes, BouncyBedsPlugin.isObfuscated);
        }

        return bytes;
    }
}