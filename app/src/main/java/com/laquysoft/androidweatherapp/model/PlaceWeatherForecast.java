package com.laquysoft.androidweatherapp.model;

import java.util.List;

/**
 * Created by joaobiriba on 29/08/14.
 */
public class PlaceWeatherForecast {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class CurrentConditionObj {
        private String cloudcover;

        public String getCloudcover() {
            return cloudcover;
        }

        public void setCloudcover(String cloudcover) {
            this.cloudcover = cloudcover;
        }
    }

    public class Data {
        private List<CurrentConditionObj> currentCondition;

        public List<CurrentConditionObj> getCurrentCondition() {
            return currentCondition;
        }

        public void setCurrentCondition(List<CurrentConditionObj> currentCondition) {
            this.currentCondition = currentCondition;
        }
    }
}
