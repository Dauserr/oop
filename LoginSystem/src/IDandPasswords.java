import java.util.HashMap;

public class IDandPasswords {
    HashMap<String,String> loginInfo = new HashMap<String,String>();

    IDandPasswords(){
        loginInfo.put("dauser","superpassword");
        loginInfo.put("daulet","megapass");
        loginInfo.put("dau","minipass");
    }

    protected HashMap<String,String> getLoginInfo(){
        return loginInfo;
    }

}
