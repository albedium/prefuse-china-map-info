package com.ooobgy.mapinfo;

import prefuse.Display;
import prefuse.data.Table;
import prefuse.data.io.CSVTableReader;
import prefuse.data.io.DataIOException;

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
        // TODO Auto-generated method stub
    }

}
