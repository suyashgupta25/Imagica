package net.sparksnetwork.app.data.remote;

import net.sparksnetwork.app.BuildConfig;

public final class ApiEndPoint {

    public static final String ENDPOINT_PICS = BuildConfig.BASE_URL + "/v0/pics";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }
}
