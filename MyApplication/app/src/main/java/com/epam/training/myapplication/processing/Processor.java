package com.epam.training.myapplication.processing;

/**
 * Created by NuclearOK on 23.10.2014.
 */
public interface Processor<ProcessingResult, Source> {

    ProcessingResult process(Source source) throws Exception;

}