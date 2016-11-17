package bouncy.beds;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

@IFMLLoadingPlugin.Name("bouncybeds")
@IFMLLoadingPlugin.MCVersion("1.11")
@IFMLLoadingPlugin.TransformerExclusions("bouncy.beds")
@IFMLLoadingPlugin.SortingIndex(1001)
public class BouncyBedsPlugin implements IFMLLoadingPlugin {

    public static boolean isObfuscated;

    @Override
    public String[] getASMTransformerClass() {
        return new String[] { "bouncy.beds.BouncyBedsTransformer" };
    }

    @Override
    public String getModContainerClass() {
        return "bouncy.beds.BouncyBedsContainer";
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        isObfuscated = (Boolean)data.get("runtimeDeobfuscationEnabled");
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
