package com.ooobgy.mapinfo;

import java.util.Iterator;

import com.ooobgy.mapinfo.conf.Config;
import com.ooobgy.mapinfo.consts.ConfConsts;

import prefuse.Display;
import prefuse.data.Table;
import prefuse.data.io.CSVTableReader;
import prefuse.data.io.DataIOException;
import prefuse.data.util.TableIterator;

/**
 * The main display (view) of the map demo.</br>
 * Before to use, it should be {@link #init(String)} with data file first.
 * <b>created:</b> 2012-3-5
 * @author 周晓龙  frogcherry@gmail.com
 */
public class MapInfoDisplay extends Display{
    private Table data;
    

    /**
     * Random Serial Version UID
     */
    private static final long serialVersionUID = -4912140410703394902L;

    public void init(String mapDataFile) throws DataIOException {
        this.data = (new CSVTableReader()).readTable(mapDataFile);
        
        for( TableIterator iterator = this.data.iterator(); iterator.hasNext();){
            //System.out.println(Config.get(ConfConsts.DATA_NAME));
            iterator.nextInt();
            System.out.println(iterator.get(Config.get(ConfConsts.DATA_NAME)));
            //System.out.println(iterator.get("error"));
        }
        // TODO Auto-generated method stub
    }

}
