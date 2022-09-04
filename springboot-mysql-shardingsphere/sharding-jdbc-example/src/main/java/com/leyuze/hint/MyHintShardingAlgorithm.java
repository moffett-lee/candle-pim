package com.leyuze.hint;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;

public class MyHintShardingAlgorithm implements HintShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(
            Collection<String> availableTargetNames,
            HintShardingValue<Long> shardingValue) {
        Collection<String> result = new ArrayList<>();
        for (String each : availableTargetNames){
            for (Long value : shardingValue.getValues()){
                if(each.endsWith(String.valueOf(value % 2))){
                    result.add(each);
                }
            }
        }
        return result;
    }
}
