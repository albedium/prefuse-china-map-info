package com.ooobgy.mapinfo.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.ooobgy.comm.util.PropertiesUtility;
import com.ooobgy.mapinfo.consts.ConfConsts;
import com.ooobgy.mapinfo.exception.IllConfException;

/**
 * 全局配置项 <b>created:</b> 2012-3-5
 * 
 * @author 周晓龙 frogcherry@gmail.com
 */
public class Config {
    private static final Properties properties = new Properties();

    private static void loadConf() {
        try {
            properties.load(new FileInputStream(new File(ConfConsts.CONF_FILE)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllConfException("Config file: " + ConfConsts.CONF_FILE + "was not found:", e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllConfException("Something wrong when reading config file: " + ConfConsts.CONF_FILE + ":", e);
        }
    }

    /**
     * 屏蔽构造
     */
    private Config() {
    }
}
