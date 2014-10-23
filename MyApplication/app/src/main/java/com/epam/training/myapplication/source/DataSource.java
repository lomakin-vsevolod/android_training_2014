package com.epam.training.myapplication.source;

/**
 * Created by NuclearOK on 23.10.2014.
 */
public interface DataSource<Result,Params>{

    Result getResult(Params params) throws Exception;

}
