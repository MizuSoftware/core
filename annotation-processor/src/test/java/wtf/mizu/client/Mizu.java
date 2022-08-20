package wtf.mizu.client;

import wtf.mizu.client.feature.MizuFeatureService;
import wtf.mizu.core.common.Plugin;

@Plugin
public class Mizu {

    private final MizuFeatureService featureService = new MizuFeatureService();

    public MizuFeatureService featureService() {
        return featureService;
    }

}
