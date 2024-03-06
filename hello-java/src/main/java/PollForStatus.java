import com.launchdarkly.sdk.server.LDClient;
import com.launchdarkly.sdk.server.LDConfig;


public class PollForStatus {
    
    static final String SDK_KEY = "sdk-1ce1a681-0013-405e-971a-***********";

    // Set FEATURE_FLAG_KEY to the feature flag key you want to evaluate.
    static final String FEATURE_FLAG_KEY = "my-boolean-flag";
  
    private static void showMessage(String s) {
        System.out.println("*** " + s);
        System.out.println();
    }
    
    public static void main(String[] args) {
        if (SDK_KEY.equals("")) {
            showMessage("Please edit Hello.java to set SDK_KEY to your LaunchDarkly SDK key first");
            System.exit(1);
        }
        
        LDConfig config = new LDConfig.Builder().build();
        LDClient client = new LDClient(SDK_KEY, config);
        
        if (client.isInitialized()) {
            showMessage("SDK successfully initialized!");
        } else {
            showMessage("SDK failed to initialize");
            System.exit(1);
        }
        
        client.getFlagTracker().addFlagChangeListener(event -> {
            System.out.printf("Flag \"%s\" has changed\n", event.getKey());
        });
        
        
        /*Timer timer = new Timer();
        timer.schedule(new TimerTask() {
          @Override
          public void run() {
            String logmsg = "Print in every second";
            System.out.println(logmsg);
          }
        }, 0, 1000);//wait 0 milliseconds before doing the action and do it every 1000ms (1 second) */
    }
}
