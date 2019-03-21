package constants;

/**
 * Common request header
 */
public final class RequestHeader {

    /**
     * Key of request header
     */
    public final class Key {

        public static final String AUTHORIZATION = "authorization";

        public static final String CLIENT_SECRET = "clientsecret";

        public static final String API_KEY = "apikey";

        public static final String SCOPE = "scope";

    }

    /**
     * Value of request header
     */
    public final class Value {

        public static final String PARENT_PORTAL_LITE = "Parent Portal Lite";

        public static final String BEARER = "Bearer {accessToken}";

    }

}
