package cn.myhydt.app.commonservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ip地址解析
 *
 * @author hy9902
 * @create 2018-08-21 11:47
 */
@Service
@Slf4j
public class IpService {
    private final static String IPURL = "http://saip.market.alicloudapi.com/ip";

    /**
     * https://market.aliyun.com/products/56928004/cmapi014125.html?spm=5176.2020520132.101.5.34unB2#sku=yuncode812500000
     *
     */

    @Autowired
    private RestTemplate restTemplate;

    private AtomicInteger flag = new AtomicInteger(0);


    @Retryable(value = RemoteAccessException.class, maxAttempts = 4)
    public String getIp(String ip, int hit) throws Exception{
        log.info("开始请求: {}, hit: {}", flag.get(), hit);
        flag.incrementAndGet();
        if(flag.get() == hit){
            return "命中成功！";
        } else {
            int k = 10/(flag.get()-1);
            if(k>0){
                throw new RemoteAccessException("K大于0, "+flag.get());
            }
        }
        return "";
    }

    @Retryable(value = Exception.class, maxAttempts = 3)
    public String getIpParse(String ip) throws Exception {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.set("Authorization", "APPCODE 7dcf83a179474d629c6edb5139b54445");
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, new URI(String.format("%s?ip=%s",IPURL, ip)));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        if(responseEntity.getStatusCodeValue() != HttpStatus.OK.value()){
            throw new Exception();
        }
        return responseEntity.getBody();
    }
}
