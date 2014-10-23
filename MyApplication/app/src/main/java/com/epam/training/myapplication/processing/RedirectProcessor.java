package com.epam.training.myapplication.processing;

/**
 * Created by NuclearOK on 23.10.2014.
 */
public class RedirectProcessor<DataSourceResult> implements Processor<DataSourceResult, DataSourceResult> {
    @Override
    public DataSourceResult process(DataSourceResult dataSourceResult) throws Exception {
        return dataSourceResult;
    }
}