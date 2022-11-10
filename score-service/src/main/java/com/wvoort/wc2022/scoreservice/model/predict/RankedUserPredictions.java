package com.wvoort.wc2022.scoreservice.model.predict;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RankedUserPredictions {



    public RankedUserPredictions() {

    }

    public List<UserScore> rank(final List<UserPredictions> userPredictions) {
        List<UserPredictions> sortedUserPredictions = userPredictions.stream().sorted().collect(Collectors.toList());
        IntStream.range(0, sortedUserPredictions.size()).forEach(i -> sortedUserPredictions.get(i).setRank(i+1));

        int rank = 1;
        UserPredictions lastUp = null;
        for(UserPredictions up : sortedUserPredictions) {
            if(lastUp == null) {
                up.setRank(rank);
            } else {

                if (lastUp.getCalculatedScore() == up.getCalculatedScore()) {
                    up.setRank(lastUp.getRank());
                } else {
                    up.setRank(rank);
                }

            }

            lastUp = up;
            rank++;
        }

        return sortedUserPredictions.stream().map(up ->
                new UserScore(up.getUser(), up.getRank(), up.getCalculatedScore())).collect(Collectors.toList());

    }


}
