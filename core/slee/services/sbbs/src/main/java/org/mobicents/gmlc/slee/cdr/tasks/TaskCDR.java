package org.mobicents.gmlc.slee.cdr.tasks;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.mobicents.gmlc.GmlcPropertiesManagement;
import org.mobicents.gmlc.slee.cdr.model.CDRModel;
import java.io.IOException;


public class TaskCDR implements Runnable {

    private final Gson gson = new Gson();
    private static final Logger logger = Logger.getLogger(TaskCDR.class);
    private static final GmlcPropertiesManagement gmlcPropertiesManagement = GmlcPropertiesManagement.getInstance();
    private final CDRModel cdrModel;

    public TaskCDR(CDRModel cdrModel) {
        this.cdrModel = cdrModel;
    }

    @Override
    public void run() {
        try {
            logger.info("Sending CDR to GLaaS");
            sendCdr();
        }catch (Exception ex) {
            logger.error("Error on try to send CDR to GLaaS " + ex.getMessage());
        }
    }

    public void sendCdr() throws IOException {
        String postUrl = gmlcPropertiesManagement.getGlaasCdrEndpoint();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(postUrl);
        StringEntity postingString = new StringEntity(gson.toJson(cdrModel));
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        post.setHeader("Authorization", "Bearer " + gmlcPropertiesManagement.getGlaasAppToken());
        HttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == 200) {
            logger.info(Thread.currentThread().getName() + " The CDR has been sent to GLaaS");
        } else {
            logger.warn(Thread.currentThread().getName() + " The GLaaS response with " + response.getStatusLine().getStatusCode());
        }
    }

}
