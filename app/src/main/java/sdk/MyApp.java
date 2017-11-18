package sdk;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.p2p.core.P2PSpecial.P2PSpecial;


/**
 * Created by dxs on 2016/11/29.
 */

public class MyApp extends Application {

    public static MyApp app;
    //two parems come frome Gwell , the value below just test
    public final static String APPID="1e9a2c3ead108413e8218a639c540e44";
    public final static String APPToken="7db7b2bff80a025a3dad546a4d5a6c3ee545568d4e0ce9609c0585c71c287d08";
    //前两位是客户APP唯一编号(00.00 由技威分配),后两位是APP版本号(客户自定义),此参数不可省略
    public final static String APPVersion="00.00.00.01";
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initP2P(app);
        initCloudChannel(app);
    }

    private void initP2P(MyApp app) {
        P2PSpecial.getInstance().init(app,APPID,APPToken,APPVersion);
    }

    /**
     * 初始化云推送通道
     * @param applicationContext
     */
    private void initCloudChannel(Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("initCloudChannel", "init cloudchannel success");
            }
            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.d("initCloudChannel", "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
