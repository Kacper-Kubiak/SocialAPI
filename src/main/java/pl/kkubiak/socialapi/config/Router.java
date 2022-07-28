package pl.kkubiak.socialapi.config;

public class Router {
    private Router() {
        throw new IllegalStateException("Utility class");
    }
    public static final String API_PATH = "/api/v1";
    public static final String USERS_URL = API_PATH+"/user";
    public static final String USER_URL = API_PATH+"/user/{id}";
    public static final String WALL_URL = USER_URL+"/wall";
    public static final String TIMELINE_URL = USER_URL+"/timeline";
    public static final String FOLLOWS_URL = USER_URL+"/follow";
    public static final String POSTS_URL = API_PATH+"/post";
    public static final String POST_URL = API_PATH+"/post/{id}";
}
