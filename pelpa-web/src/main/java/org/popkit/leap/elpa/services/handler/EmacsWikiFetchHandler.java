package org.popkit.leap.elpa.services.handler;

import org.popkit.leap.elpa.entity.FetcherEnum;
import org.popkit.leap.elpa.entity.RecipeDo;
import org.popkit.leap.elpa.services.FetchHandler;
import org.popkit.leap.elpa.services.HttpProxyService;
import org.popkit.leap.elpa.utils.PelpaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Aborn Jiang
 * Mail aborn.jiang@gmail.com
 * 2016-05-23:21:29
 */
@Service
public class EmacsWikiFetchHandler implements FetchHandler {
    private static final String WIKI_ROOT = "https://www.emacswiki.org/emacs/download/";

    @Autowired
    private HttpProxyService httpProxyService;

    public boolean validate(RecipeDo recipeDo, Map<String, Object> extra) {
        return recipeDo.getFetcherEnum() == FetcherEnum.WIKI;
    }

    public void execute(RecipeDo recipeDo, Map<String, Object> extra) {
        String wikiUrl = WIKI_ROOT + recipeDo.getPkgName() + ".el";
        String localWorking = PelpaUtils.getWorkingPath(recipeDo.getPkgName());
        System.out.println("wikiUrl=" + wikiUrl + "\nlocalWorking=" + localWorking);
        /*
        try {
            if (!FetchRemoteFileUtils.downloadFile(wikiUrl, localWorking + "/" + recipeDo.getPkgName() + ".el")) {
                LeapLogger.warn("error download pkg:" + recipeDo.getPkgName() + " from " + wikiUrl);
            }
        } catch (Exception e) {
            LeapLogger.warn("exception error download pkg:" + recipeDo.getPkgName() + " from " + wikiUrl);
        }*/
    }
}
