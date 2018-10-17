package cn.myhydt.app.commonservice.sp;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * 小程序工具类
 *
 * @author hy9902
 * @create 2018-10-16 19:24
 */
@Slf4j
public class SpUtil {

    /**
     * 解密小程序用户信息
     * 1. wx.login()获取spCode
     * 2. spCode发送给后台获取session key, openid, unionid
     * 3. wx.getUserInfo() 获取 userInfo, rawData, signature, encryptedData, iv
     * 4. 将获取的数据发送给后台进行解密获取用户数据
     *
     * 其中 signature = sha1( rawData + session_key )
     * <p>
     * 微信接口文档：
     * <p>
     * https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/login.html
     * <p>
     * 的 《code 换取 session_key》 章节
     *
     * @param appId 用户小程序id
     *
     * @param appSecret 用户小程序secret
     *
     * @return
     */
    public String getWxUser(String appId, String appSecret, String spCode, String signature, String iv, String rawData){

        return null;
    }
}
